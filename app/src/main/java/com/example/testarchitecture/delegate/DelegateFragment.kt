package com.example.testarchitecture.delegate

import android.os.Bundle
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.example.testarchitecture.DelegateViewModelFactory
import com.example.testarchitecture.provide
import dagger.android.support.DaggerFragment
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
class DelegateFragment : DaggerFragment() {

    @Inject
    lateinit var assistedFactory: DelegateViewModelFactory

    private val viewModel: DelegateViewModel by viewModels {
        assistedFactory.provide(this, arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.navigationBarColor = android.graphics.Color.YELLOW
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsState()
                val topBarState by viewModel.topBarState.collectAsState()
                val contentState by viewModel.contentState.collectAsState()

                Scaffold(
                    containerColor = Color.Yellow,
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.shadow(4.dp),
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Yellow),
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowLeft,
                                    contentDescription = null,
                                    modifier = Modifier.clickable {
                                        activity?.supportFragmentManager?.popBackStack()
                                    }
                                )
                            },
                            title = {
                                Text(state.title)
                            }
                        )
                    },
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .padding(vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(state.content)
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = { viewModel.updateTitle("Updated Delegate Title") }
                            ) {
                                Text("Update title")
                            }
                            Button(
                                onClick = { viewModel.updateContent("Updated Delegate Text") }
                            ) {
                                Text("Update text")
                            }
                        }
                    }
                )
            }
        }
    }
}