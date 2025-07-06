package com.example.testarchitecture.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.testarchitecture.ComponentViewModelFactory
import com.example.testarchitecture.provide
import dagger.android.support.DaggerFragment
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
class ComponentFragment : DaggerFragment() {

    @Inject
    lateinit var assistedFactory: ComponentViewModelFactory

    private val viewModel: ComponentViewModel by viewModels {
        assistedFactory.provide(this, arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.navigationBarColor = android.graphics.Color.GREEN
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Scaffold(
                    containerColor = Color.Green,
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.shadow(4.dp),
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Green),
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
                                Text("ComponentFragment")
                            }
                        )
                    },
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {

                        }
                    }
                )
            }
        }
    }
}