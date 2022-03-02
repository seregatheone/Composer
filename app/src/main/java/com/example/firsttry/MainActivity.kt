package com.example.firsttry

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                    Column(modifier = Modifier.padding(5.dp)) {
                        HelloContentWithoutArgs()
                        TestStates()
                        TestViewModelStates()
                    }
                }
        }
    }
}
//stateless and stateful composable
@Composable
fun TestStates(){
    var firstInt  by rememberSaveable{ mutableStateOf(3)}
    Log.i("recomposed",firstInt.toString())
    ShowMyState(firstInt){firstInt+=1}
}
@Composable
fun ShowMyState(integer : Int,onIncInt : ()->Unit){
    Column(modifier = Modifier.fillMaxWidth()){
        Button(onClick = onIncInt) {
            Text(text = "Push to update ints")
        }
        Text(text = "Check my integer $integer")
    }

}
//use viewModel
@Composable
fun TestViewModelStates(viewModel: MyViewModel = MyViewModel()){
    val count : Int by viewModel.count.observeAsState(12)
    ShowMyState(count){viewModel.releaseNewInteger(count+ 13)}
}
@Composable
fun HelloContentWithoutArgs() {
    var surname by remember { mutableStateOf("Demidovich") }
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("Sergey") }
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name $surname!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = { Text("Surname") }
        )
    }
}
