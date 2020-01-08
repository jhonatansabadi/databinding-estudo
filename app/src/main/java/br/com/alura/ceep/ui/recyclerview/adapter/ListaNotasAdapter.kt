package br.com.alura.ceep.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.BR
import br.com.alura.ceep.R
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.ui.extensions.carregaImagem
import kotlinx.android.synthetic.main.item_nota.view.*

class ListaNotasAdapter(
    private val context: Context,
    var onItemClickListener: (notaSelecionada: Nota) -> Unit = {}
) : ListAdapter<Nota, ListaNotasAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(context)
        val viewDataBinding =
            DataBindingUtil.inflate<ViewDataBinding>(inflator, R.layout.item_nota, parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { nota ->
            holder.vincula(nota)
        }
    }

    inner class ViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        private lateinit var nota: Nota

        init {
            itemView.setOnClickListener {
                if (::nota.isInitialized) {
                    onItemClickListener(nota)
                }
            }
        }

        fun vincula(nota: Nota) {
            this.nota = nota
            dataBinding.setVariable(BR.nota, nota)
        }
    }

}

object DiffCallback : DiffUtil.ItemCallback<Nota>() {
    override fun areItemsTheSame(
        oldItem: Nota,
        newItem: Nota
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Nota, newItem: Nota) = oldItem == newItem

}

@BindingAdapter("carregaImagem")
fun carregaImagemPorUrl(view: ImageView, url: String) {
    view.carregaImagem(url)
}