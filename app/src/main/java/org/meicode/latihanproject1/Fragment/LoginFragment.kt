package org.meicode.latihanproject1.Fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import org.meicode.latihanproject1.Database.User
import org.meicode.latihanproject1.R
import org.meicode.latihanproject1.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    lateinit var bindingLogin: FragmentLoginBinding
    lateinit var dbRef:DatabaseReference

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        bindingLogin = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        bindingLogin.tvRegister.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        bindingLogin.btnLogin.setOnClickListener { view:View ->
            val email = bindingLogin.edtEmailLogin.text.toString()
            val password = bindingLogin.edtPasswordLogin.text.toString()

            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    for (postsnapshot in dataSnapshot.children) {
                        val value = postsnapshot.getValue<User>()
                        if (value!!.email == email && value!!.password == password) {
                            view.findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                            Toast.makeText(
                                context,
                                "Login Successfull",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                " Please Check Your Email And Password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }

            })
        }
        return bindingLogin.root
    }
}