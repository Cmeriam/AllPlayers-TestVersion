package edu.farmingdale.alrajab.week12_auth_ml_api_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import edu.farmingdale.alrajab.week12_auth_ml_api_demo.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }

    lateinit var binding: ActivityLandingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutBtn.setOnClickListener { logout() }


        firebaseAuth = FirebaseAuth.getInstance()

        binding.loadIamgeBtn.setOnClickListener {
            pickImageFromGallery()
        }

    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imageHolder.setImageURI(data?.data)
        }
    }

    private fun logout() {
        firebaseAuth.signOut()
            startActivity(Intent(this@LandingActivity,LoginActivity::class.java))

    }
}


