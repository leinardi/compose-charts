package me.bytebeats.views.charts.app.ui.screen.line

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.bytebeats.views.charts.app.ui.ScreenRouter
import me.bytebeats.views.charts.app.ui.theme.Margins
import me.bytebeats.views.charts.line.LineChart

/**
 * Created by bytebeats on 2021/9/30 : 17:55
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

@Composable
fun LineChartScreen() {
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { ScreenRouter.navigateHome() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go back home"
                    )
                }
            },
            title = { Text(text = "Line Chart") }
        )
    }) {
        LineChartContent(Modifier.padding(it))
    }
}

@Composable
private fun LineChartContent(
    modifier: Modifier = Modifier
) {
    val lineChartData = LineChartDataModel()

    Column(
        modifier = modifier.padding(
            horizontal = Margins.horizontal,
            vertical = Margins.vertical
        )
    ) {
        LineChartRow(lineChartDataModel = lineChartData)
        HorizontalOffsetSelector(lineChartDataModel = lineChartData)
        OffsetProgress(lineChartDataModel = lineChartData)
    }
}

@Composable
private fun LineChartRow(lineChartDataModel: LineChartDataModel) {
    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxSize()
    ) {
        LineChart(
            lineChartData = lineChartDataModel.lineChartData,
            horizontalOffset = lineChartDataModel.horizontalOffset,
            pointDrawer = lineChartDataModel.pointDrawer
        )
    }
}

@Composable
private fun HorizontalOffsetSelector(lineChartDataModel: LineChartDataModel) {
    val pointDrawType = lineChartDataModel.pointDrawerType
    Column(
        modifier = Modifier.padding(horizontal = Margins.horizontal, vertical = Margins.vertical),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Point Drawer")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = Margins.horizontal, vertical = Margins.vertical),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (drawerType in PointDrawerType.values()) {
                OutlinedButton(
                    onClick = { lineChartDataModel.pointDrawerType = drawerType },
                    border = ButtonDefaults.outlinedBorder.takeIf { pointDrawType == drawerType },
                ) {
                    Text(text = drawerType.name)
                }
            }
        }
    }
}

@Composable
private fun OffsetProgress(lineChartDataModel: LineChartDataModel) {
    Column(
        modifier = Modifier.padding(horizontal = Margins.horizontal),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Offset")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Margins.horizontal, vertical = Margins.vertical)
                .align(Alignment.CenterHorizontally)
        ) {
            Slider(
                value = lineChartDataModel.horizontalOffset,
                onValueChange = { lineChartDataModel.horizontalOffset = it },
                valueRange = 0F.rangeTo(25F)
            )
        }
    }
}
