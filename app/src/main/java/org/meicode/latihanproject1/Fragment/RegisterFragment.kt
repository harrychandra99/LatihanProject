package org.meicode.latihanproject1.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.meicode.latihanproject1.Database.User
import org.meicode.latihanproject1.R
import org.meicode.latihanproject1.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var bindingRegisterFragment : FragmentRegisterBinding
    private lateinit var dbRef: DatabaseReference


    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        bindingRegisterFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        bindingRegisterFragment.btnRegister.setOnClickListener {view : View ->
            val email = bindingRegisterFragment.edtEmailRegister.text.toString()
            val password = bindingRegisterFragment.edtPasswordRegister.text.toString()

            dbRef = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(email, password)

            if(email.isEmpty() && password.isEmpty()){
                Toast.makeText(context, "Please Fill Your Email And Password", Toast.LENGTH_SHORT).show()
            } else{
                dbRef.child(email).setValue(user).addOnSuccessListener {
                    bindingRegisterFragment.edtEmailRegister.text.clear()
                    bindingRegisterFragment.edtPasswordRegister.text.clear()
                    Toast.makeText(context, "Sucessful saved", Toast.LENGTH_SHORT).show()
                    view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }.addOnFailureListener{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return bindingRegisterFragment.root
    }

}