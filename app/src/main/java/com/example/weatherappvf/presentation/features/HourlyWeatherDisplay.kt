import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherappvf.domain.model.forcast_weather.ForcastWeather
import com.example.weatherappvf.domain.model.weather.WeatherType
import com.example.weatherappvf.core.utils.Utils

@Composable
fun HourlyWeatherDisplay(
    weatherData: ForcastWeather,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = weatherData.weather.firstOrNull()?.main ?: "No description available",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.White
        )

        val weatherType = getWeatherType(weatherData.weather.firstOrNull()?.main)
        Image(
            painter = painterResource(id = weatherType.iconRes),
            contentDescription = null,
             modifier = Modifier
                .width(50.dp)
                .height(50.dp)
        )
        val formattedTemperature = "%.2f".format(weatherData.main.temp - 273.15)
        Text(
            text = "$formattedTemperature °C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
        Text(
            text =  Utils.formatDate(weatherData.dt_txt),
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun getWeatherType(main: String?): WeatherType {
    return when (main) {
        "Clear" -> WeatherType.ClearSky
        "Rain" -> WeatherType.ModerateRain
        "Clouds" -> WeatherType.PartlyCloudy
        else -> WeatherType.MainlyClear
    }
}
