package org.heartimaging.echoindications.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.heartimaging.echoindications.auth.AuthManager
import org.heartimaging.echoindications.ui.CdsScreen
import org.heartimaging.echoindications.ui.EditIndicationScreen
import org.heartimaging.echoindications.ui.FeedbackScreen
import org.heartimaging.echoindications.ui.IndicationDetailScreen
import org.heartimaging.echoindications.ui.IndicationsListScreen
import org.heartimaging.echoindications.ui.LandingScreen
import org.heartimaging.echoindications.ui.LoginScreen

object Routes {
    const val LANDING = "landing"
    const val INDICATIONS = "indications"
    const val INDICATION_DETAIL = "indications/{id}"
    const val INDICATION_EDIT = "indications/{id}/edit"
    const val CDS_ROOT = "cds"
    const val CDS_CHILD = "cds/{parentId}"
    const val LOGIN = "login"
    const val FEEDBACK = "feedback"

    fun indicationDetail(id: Int) = "indications/$id"
    fun indicationEdit(id: Int) = "indications/$id/edit"
    fun cdsChild(parentId: Int) = "cds/$parentId"
}

@Composable
fun AppNavigation(auth: AuthManager) {
    val nav: NavHostController = rememberNavController()

    NavHost(navController = nav, startDestination = Routes.LANDING) {
        composable(Routes.LANDING) {
            LandingScreen(auth = auth, nav = nav)
        }
        composable(Routes.INDICATIONS) {
            IndicationsListScreen(auth = auth, nav = nav)
        }
        composable(Routes.INDICATION_DETAIL) { backStack ->
            val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 0
            IndicationDetailScreen(indicationId = id, auth = auth, nav = nav)
        }
        composable(Routes.INDICATION_EDIT) { backStack ->
            val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 0
            EditIndicationScreen(indicationId = id, auth = auth, nav = nav)
        }
        composable(Routes.CDS_ROOT) {
            CdsScreen(parentId = null, auth = auth, nav = nav)
        }
        composable(Routes.CDS_CHILD) { backStack ->
            val parentId = backStack.arguments?.getString("parentId")?.toIntOrNull()
            CdsScreen(parentId = parentId, auth = auth, nav = nav)
        }
        composable(Routes.LOGIN) {
            LoginScreen(auth = auth, nav = nav)
        }
        composable(Routes.FEEDBACK) {
            FeedbackScreen(auth = auth, nav = nav)
        }
    }
}
