package com.nsdevelopment.mobile.utilities

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showBackground = false, showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = false, showSystemUi = false,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
annotation class SystemPreviews