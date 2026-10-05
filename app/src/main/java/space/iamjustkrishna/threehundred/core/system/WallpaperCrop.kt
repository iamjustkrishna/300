package space.iamjustkrishna.threehundred.core.system

import kotlin.math.roundToInt

data class WallpaperCrop(
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int
) {
    companion object {
        fun calculate(
            sourceWidth: Int,
            sourceHeight: Int,
            viewportWidth: Int,
            viewportHeight: Int,
            horizontalOffset: Float,
            verticalOffset: Float
        ): WallpaperCrop {
            require(sourceWidth > 0 && sourceHeight > 0)
            require(viewportWidth > 0 && viewportHeight > 0)

            val viewportAspect = viewportWidth.toFloat() / viewportHeight
            val sourceAspect = sourceWidth.toFloat() / sourceHeight
            val cropWidth: Int
            val cropHeight: Int
            if (sourceAspect > viewportAspect) {
                cropHeight = sourceHeight
                cropWidth = (sourceHeight * viewportAspect).roundToInt().coerceAtMost(sourceWidth)
            } else {
                cropWidth = sourceWidth
                cropHeight = (sourceWidth / viewportAspect).roundToInt().coerceAtMost(sourceHeight)
            }
            val maxLeft = sourceWidth - cropWidth
            val maxTop = sourceHeight - cropHeight
            val left = (maxLeft * ((horizontalOffset.coerceIn(-1f, 1f) + 1f) / 2f))
                .roundToInt()
                .coerceIn(0, maxLeft)
            val top = (maxTop * ((verticalOffset.coerceIn(-1f, 1f) + 1f) / 2f))
                .roundToInt()
                .coerceIn(0, maxTop)

            return WallpaperCrop(left, top, cropWidth, cropHeight)
        }
    }
}
