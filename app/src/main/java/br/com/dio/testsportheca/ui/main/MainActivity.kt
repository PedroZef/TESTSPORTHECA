package br.com.dio.testsportheca.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import br.com.dio.testsportheca.R
import br.com.dio.testsportheca.databinding.ActivityMainBinding
import br.com.dio.testsportheca.utils.Failed
import br.com.dio.testsportheca.utils.Loading
import br.com.dio.testsportheca.utils.Success
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        setupObserver()
    }

    fun setupObserver(){
        lifecycleScope.launchWhenCreated {
            viewModel._resultLiveData.collect {
                when(it){
                    is Loading -> {}
                    is Failed -> {}
                    is Success -> {
                        it.data?.objectList?.firstOrNull()?.player?.apply {
                            binding?.imgPhoto?.let { it1 ->
                                Glide.with(this@MainActivity).load(this.img).circleCrop().into(
                                    it1
                                )
                            }
                            binding?.textUserName?.text = this.name
                            binding?.textCountry?.text = this.country
                            binding?.textPosition?.text = this.pos
                            binding?.textPercentual?.text = "%.3f".format(this.percentual)
                            binding?.textPlaWon?.text = this.barras?.copasDoMundoVencidas?.pla.toString()
                            binding?.textPlaDiputed?.text = this.barras?.copasDoMundoDisputadas?.pla.toString()
                            binding?.textPosWon?.text = "${this.barras?.copasDoMundoVencidas?.pos.toString()}º"
                            binding?.textPosDiputed?.text = "${this.barras?.copasDoMundoDisputadas?.pos.toString()}º"
                            binding?.progressBar?.max = this.barras?.copasDoMundoVencidas?.max?.toInt() ?: 0
                            binding?.progressBar?.progress = this.barras?.copasDoMundoVencidas?.pla?.toInt() ?: 0
                            binding?.progressBarDisputed?.max = this.barras?.copasDoMundoDisputadas?.max?.toInt() ?: 0
                            binding?.progressBarDisputed?.progress = this.barras?.copasDoMundoDisputadas?.pla?.toInt() ?: 0
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}