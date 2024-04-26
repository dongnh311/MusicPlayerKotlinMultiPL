//
//  FirebaseLoginControl.swift
//  iosApp
//
//  Created by Nguyen Hoai Dong on 17/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import ComposeApp
import GoogleSignIn
import FirebaseAuth
import FirebaseCore

/**
 * Control login with google
 */
class FirebaseGoogleControl: IOsFireBaseAuth {
    
    let timeInterval = Int64(Date().timeIntervalSince1970 * 1000)
    
    // Login with google
    override func logInWithGoogle() {
        var clientID = FirebaseApp.app()?.options.clientID
        if ((clientID?.isEmpty) != nil) { clientID = "997870831832-cq188cctf89e1kfpo75g5nfq7av72tlu.apps.googleusercontent.com"}
            
        print("logInWithGoogle clientId: \(String(describing: clientID))")
            
        // Create Google Sign In configuration object.
        let config = GIDConfiguration(clientID: clientID ?? "997870831832-cq188cctf89e1kfpo75g5nfq7av72tlu.apps.googleusercontent.com")
        GIDSignIn.sharedInstance.configuration = config
        
        
        do {
            // Find view
            guard let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene,
                      let window = windowScene.windows.first,
                      let rootViewController = window.rootViewController else {
                  print("There is no root view controller!")
                  return
                }
            
            // Start the sign in flow!
            GIDSignIn.sharedInstance.signIn(withPresenting: rootViewController) { result, error in
                guard error == nil else {
                    fatalError("Login with google fail")
                }

                guard let user = result?.user,
                      let idToken = user.idToken?.tokenString
                else {
                    print("Fail to login with google")
                    fatalError("User of google have issue")
                }

                let credential = GoogleAuthProvider.credential(withIDToken: idToken,
                                                             accessToken: user.accessToken.tokenString)

                Auth.auth().signIn(with: credential) { result, error in
                    guard error == nil else {
                        fatalError("Login with firebase with google token fail")
                    }

                    // At this point, our user is signed in
                    let user = result?.user
                    print("Login with google done : UserId: \(String(describing: user?.uid))")
                    
                    let userModelSendBack = UserModel()
                    userModelSendBack.id = user?.uid ?? ""
                    userModelSendBack.userName = user?.displayName ?? ""
                    userModelSendBack.profileImage = user?.photoURL?.absoluteString ?? ""
                    print("onLoginGoogleCallBack user profileImage: \(String(describing: userModelSendBack.profileImage))")
                    
                    userModelSendBack.loginType = UserConstKt.LOGIN_BY_GOOGLE
                    userModelSendBack.platform = UserConstKt.PLATFORM_ANDROID
                    
                    self.onLoginGoogleCallBack?.onLoginComplete(userModel: userModelSendBack)
                    print("onLoginGoogleCallBack callback user: \(String(describing: userModelSendBack))")
                }
            }
        } catch {
            self.onLoginGoogleCallBack?.onLoginFail(exception: error as! KotlinException)
            print(error.localizedDescription)
        }
    }
}
