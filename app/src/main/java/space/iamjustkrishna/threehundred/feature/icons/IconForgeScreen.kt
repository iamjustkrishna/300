package space.iamjustkrishna.threehundred.feature.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.iamjustkrishna.threehundred.core.designsystem.CanvasDeepBasaltVoid
import space.iamjustkrishna.threehundred.core.designsystem.CleanSansFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldHairlineRim
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldPrimary
import space.iamjustkrishna.threehundred.core.designsystem.SpartanSerifFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceBasaltStone
import space.iamjustkrishna.threehundred.core.designsystem.TextHighContrastBone
import space.iamjustkrishna.threehundred.core.designsystem.TextWarmSilverMuted

@Composable
fun IconForgeScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    var selectedFamily by remember { mutableStateOf("all") }
    var selectedGlyph by remember { mutableStateOf<IconGlyph?>(null) }
    val families = listOf("all") + IconCatalog.map { it.family }.distinct()
    val filtered = IconCatalog.filter {
        (selectedFamily == "all" || it.family == selectedFamily) &&
            (query.isBlank() || it.title.contains(query, ignoreCase = true) || it.id.contains(query, ignoreCase = true))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasDeepBasaltVoid)
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 18.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back",
                tint = TextHighContrastBone,
                modifier = Modifier.size(24.dp).clickable(onClick = onBack)
            )
            Spacer(Modifier.width(14.dp))
            Column {
                Text("ICON FORGE", fontFamily = SpartanSerifFontFamily, fontSize = 20.sp, color = TextHighContrastBone)
                Text("96 BRONZE GLYPHS · ORIGINAL VECTOR CATALOG", fontFamily = CleanSansFontFamily, fontSize = 9.sp, color = SatinGoldHairlineRim)
            }
        }
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Search the arsenal", color = TextWarmSilverMuted) },
            leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null, tint = SatinGoldPrimary) }
        )
        androidx.compose.foundation.lazy.LazyRow(
            contentPadding = PaddingValues(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(families) { family ->
                Box(
                    modifier = Modifier
                        .border(1.dp, if (selectedFamily == family) SatinGoldPrimary else SatinGoldHairlineRim, RoundedCornerShape(18.dp))
                        .background(if (selectedFamily == family) SatinGoldPrimary else SurfaceBasaltStone, RoundedCornerShape(18.dp))
                        .clickable { selectedFamily = family }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        family.replaceFirstChar { it.uppercase() },
                        fontFamily = CleanSansFontFamily,
                        fontSize = 10.sp,
                        color = if (selectedFamily == family) CanvasDeepBasaltVoid else TextWarmSilverMuted
                    )
                }
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filtered.chunked(2)) { pair ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                    pair.forEach { glyph ->
                        IconGlyphCard(glyph, Modifier.weight(1f), onClick = { selectedGlyph = glyph })
                    }
                    if (pair.size == 1) Spacer(Modifier.weight(1f))
                }
            }
        }
    }
    selectedGlyph?.let { glyph ->
        androidx.compose.ui.window.Dialog(onDismissRequest = { selectedGlyph = null }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceBasaltStone, RoundedCornerShape(20.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("GLYPH DETAIL", fontFamily = CleanSansFontFamily, fontSize = 10.sp, color = SatinGoldHairlineRim)
                Spacer(Modifier.height(14.dp))
                Text("Λ", fontFamily = SpartanSerifFontFamily, fontSize = 56.sp, color = SatinGoldPrimary)
                Text(glyph.title, fontFamily = SpartanSerifFontFamily, fontSize = 20.sp, color = TextHighContrastBone)
                Text("${glyph.family.uppercase()} · ORIGINAL VECTOR CATALOG", fontFamily = CleanSansFontFamily, fontSize = 9.sp, color = TextWarmSilverMuted)
                Spacer(Modifier.height(16.dp))
                Text("Launcher import and replacement behavior depends on the user's launcher.", fontFamily = CleanSansFontFamily, fontSize = 11.sp, color = TextWarmSilverMuted)
                OutlinedButton(onClick = { selectedGlyph = null }, modifier = Modifier.padding(top = 16.dp)) {
                    Text("CLOSE", color = SatinGoldPrimary)
                }
            }
        }
    }
}

@Composable
private fun IconGlyphCard(glyph: IconGlyph, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .background(SurfaceBasaltStone, RoundedCornerShape(14.dp))
            .border(1.dp, Color(0x1FC5A869), RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(52.dp).background(CanvasDeepBasaltVoid, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Λ", fontFamily = SpartanSerifFontFamily, fontSize = 26.sp, color = SatinGoldPrimary)
        }
        Spacer(Modifier.height(6.dp))
        Text(glyph.title, fontFamily = CleanSansFontFamily, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = TextHighContrastBone)
        Text(glyph.family.uppercase(), fontFamily = CleanSansFontFamily, fontSize = 8.sp, color = SatinGoldHairlineRim)
    }
}
