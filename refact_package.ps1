param (
    [string]$OldPkg,
    [string]$NewPkg,
    [string]$BasePath,
    [string]$LogFile
)

# ===== TEXT REPLACE =====
Add-Content $LogFile "==============================="
Add-Content $LogFile ("Refactor started: " + (Get-Date))
Add-Content $LogFile ("$OldPkg -> $NewPkg")
Add-Content $LogFile ("Base path: $BasePath")
Add-Content $LogFile "==============================="

Get-ChildItem $BasePath -Recurse -File `
    -Include *.java,*.kt,*.xml,*.gradle,*.kts,*.properties,*.pro |
ForEach-Object {
    $content = Get-Content $_.FullName
    if ($content -like ("*" + $OldPkg + "*")) {
        ($content -replace [regex]::Escape($OldPkg), $NewPkg) |
            Set-Content $_.FullName
        Add-Content $LogFile ("UPDATED FILE: " + $_.FullName)
    }
}
# ===== RENAME PACKAGE FOLDER =====
$oldPath = ($OldPkg -replace '\.', '\')
$newPath = ($NewPkg -replace '\.', '\')

$javaRoots = @(
    (Join-Path $BasePath "src\main\java"),
    (Join-Path $BasePath "src\main\kotlin")
)

foreach ($root in $javaRoots) {
    if (Test-Path $root) {
        Get-ChildItem $root -Recurse -Directory |
        Where-Object { $_.FullName -like ("*" + $oldPath) } |
        ForEach-Object {
            $target = $_.FullName -replace [regex]::Escape($oldPath), $newPath
            Add-Content $LogFile ("MOVE DIR: " + $_.FullName + " -> " + $target)

            if (!(Test-Path $target)) {
                New-Item -ItemType Directory -Path $target | Out-Null
            }

            Copy-Item "$($_.FullName)\*" $target -Recurse -Force
            Remove-Item $_.FullName -Recurse -Force
        }
    }
}

Add-Content $LogFile ("Refactor finished: " + (Get-Date))
Add-Content $LogFile "==============================="
