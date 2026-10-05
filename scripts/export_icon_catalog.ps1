param(
    [string]$OutputDirectory = "assets\icons\exports"
)

$families = @{
    core = "phone messages mail camera gallery browser maps contacts clock calendar calculator files settings security play music"
    focus = "fitness health timer notes tasks journal wallet weather travel home work study book podcast photo voice"
    system = "wifi bluetooth battery flashlight volume brightness airplane location lock unlock shield sync download upload share search"
    forge = "lambda helmet spear shield laurel torch column crest anvil hammer standard gate compass orbit medallion vault"
    social = "people profile community event chat call video stream follow heart bookmark comment send link qr inbox"
    commerce = "store cart receipt card bank chart coin gift ticket package delivery support star crown vanguard restore"
}

New-Item -ItemType Directory -Force $OutputDirectory | Out-Null
$bronze = "#C5A869"
$dark = "#08080A"

foreach ($family in $families.Keys) {
    foreach ($name in $families[$family].Split(" ")) {
        $id = "${family}_${name}"
        $label = $name.Substring(0, 1).ToUpper() + $name.Substring(1)
        $svg = @"
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" role="img" aria-labelledby="title">
  <title>300 $label icon glyph</title>
  <rect width="512" height="512" rx="96" fill="$dark"/>
  <path d="M128 384 256 112 384 384M176 296h160" fill="none" stroke="$bronze" stroke-width="24" stroke-linecap="round" stroke-linejoin="round"/>
  <text x="256" y="448" text-anchor="middle" fill="$bronze" font-family="sans-serif" font-size="24" letter-spacing="4">$label</text>
</svg>
"@
        [IO.File]::WriteAllText((Join-Path $OutputDirectory "$id.svg"), $svg)
    }
}

Write-Output "Exported $((Get-ChildItem $OutputDirectory -Filter *.svg).Count) SVG glyphs to $OutputDirectory"
