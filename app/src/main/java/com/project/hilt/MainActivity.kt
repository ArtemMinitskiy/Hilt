package com.project.hilt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.hilt.ui.theme.HiltTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

//Аннотация @AndroidEntryPoint говорит Hilt, чтобы он генерировал классы Component.
//Каждый компонент отвечает за зависимости своего класса, ActivityComponent за Activity, FragmentComponent за Fragment.
//Именно эта аннотация позволяет нам в дальнейшем привязывать зависимости к Fragment, Activity и т.д..
//Также если вы указали эту аннотацию, например, у какого-то фрагмента, её также необходимо указать у Activity, к которой он привязан.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private val viewModel: MainViewModel by viewModels()

//The field injection can be done by the @Inject keyword, which lets the Hilt fill the required field with the proper class instance.
//The field injection is handy during class inheritance, which we do not possess from Android like Activity, Service, Fragment, View and others.
//    @Inject
//    lateinit var repository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            //Compose Way
            val viewModel: MainViewModel = hiltViewModel()
            var data by remember { mutableStateOf("") }

            HiltTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = data,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            LaunchedEffect(viewModel) {
                viewModel.stateFlow.collectLatest {
                    data = it.ifEmpty { "Empty" }
                }
            }

            LaunchedEffect(viewModel) {
                viewModel.stateDataFlow.collectLatest {
                    if (!it.isNullOrEmpty()) {
                        it.forEach { data ->
                            Log.i("mLogHilt", "Data Id: ${data.someDataId}")
                        }
                    }
                }
            }

//            LaunchedEffect(Unit) {
//                repository.getSomeData("Data")
//            }
        }
    }
}