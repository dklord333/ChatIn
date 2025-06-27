import androidx.lifecycle.ViewModel
import com.example.chatin.ModelClass.UserModel
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val _user = MutableStateFlow(auth.currentUser)
    val user: StateFlow<FirebaseUser?> = _user.asStateFlow()

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _user.value = auth.currentUser
                    onResult(true, null)
                    Firebase.analytics.logEvent("login_success", null)

                } else {
                    onResult(false, it.exception?.message)
                }
            }
    }


    fun register(credentials: UserModel, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(credentials.email, credentials.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _user.value = auth.currentUser
                    onResult(true, null)
                } else {
                    onResult(false, it.exception?.message)
                }
            }
    }


    fun logout() {
        auth.signOut()
        _user.value = null
    }
}
