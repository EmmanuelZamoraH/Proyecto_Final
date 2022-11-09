import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_notas.R
import com.example.proyecto_notas.data.noteDatabase
import com.example.proyecto_notas.model.Note
import com.example.proyecto_notas.note_adapter
import kotlinx.coroutines.launch


class note_list : Fragment() {
    lateinit var notes : List<Note>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_note_list, container, false)

        val rv = root.findViewById<RecyclerView>(R.id.rv_note_list)

        lifecycleScope.launch{
            notes  = noteDatabase.getDatabase(requireActivity().applicationContext).noteDao().getAllNotes()
        }

        rv.adapter = note_adapter(notes)
        rv.layoutManager = LinearLayoutManager(this@note_list.requireContext())
        // Inflate the layout for this fragment

        root.findViewById<Button>(R.id.btn_home).setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_note_list_to_mainMenu)
        }

        return root.rootView
    }

}