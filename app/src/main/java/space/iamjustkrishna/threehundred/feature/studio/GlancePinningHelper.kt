package space.iamjustkrishna.threehundred.feature.studio

import android.content.Context
import space.iamjustkrishna.threehundred.feature.glance.GlancePinningHelper as FeatureGlancePinningHelper

/**
 * Delegating helper in feature.studio forwarding to feature.glance.GlancePinningHelper.
 */
object GlancePinningHelper {

    fun pinMonolithClock(context: Context) {
        FeatureGlancePinningHelper.pinMonolithClock(context)
    }

    fun pinShieldBattery(context: Context) {
        FeatureGlancePinningHelper.pinShieldBattery(context)
    }
}
