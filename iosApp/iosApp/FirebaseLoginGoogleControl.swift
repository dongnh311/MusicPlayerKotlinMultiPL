//
//  FirebaseLoginGoogleControl.swift
//  iosApp
//
//  Created by Nguyen Hoai Dong on 17/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import ComposeApp


class FirebaseLoginGoogleControl: IOsFireBaseAuth {
    
    // Login with google
    override func logInWithGoogle() {
        
        guard let clientID = FirebaseApp.app()?.options.clientID else { return }
            
        print("logInWithGoogle clientId: \(clientID)")
            
        // Create Google Sign In configuration object.
        let config = GIDConfiguration(clientID: clientID)
        GIDSignIn.sharedInstance.configuration = config
            
        // Start the sign in flow!
        GIDSignIn.sharedInstance.signIn(withPresenting: self) { [unowned self] result, error in
              guard error == nil else {
                  print("logInWithGoogle error : \(error)")
              }

              guard let user = result?.user,
                let idToken = user.idToken?.tokenString
                print("logInWithGoogle idToken : \(idToken)")
              else {
                
              }

              let credential = GoogleAuthProvider.credential(withIDToken: idToken,
                                                             accessToken: user.accessToken.tokenString)

             
              Auth.auth().signIn(with: credential) { result, error in
                  guard error == nil else {
                      print("logInWithGoogle Auth signIn error: \(error)")
                  }
                  guard let user = result?.user,
                            user.userID
                  else {
                      //
                      print("logInWithGoogle Auth signIn error: \(error)")
                  }
              }
        }
    }
}
