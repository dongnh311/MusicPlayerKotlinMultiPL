//
//  FirebaseFCMHelper.swift
//  iosApp
//
//  Created by Nguyen Hoai Dong on 02/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import FirebaseMessaging
import ComposeApp

class FirebaseFCMHelper {
    var fcmRegTokenMessage = ""
    
    func loadFCMToken() {
        Messaging.messaging().token { token, error in
          if let error = error {
            print("Error fetching FCM registration token: \(error)")
          } else if let token = token {
            print("FCM registration token: \(token)")
            self.fcmRegTokenMessage  = "Remote FCM registration token: \(token)"
            FirebaseLoginShare_iosKt.iosFireBaseAuth.fcmToken = self.fcmRegTokenMessage
          }
        }
    }
}
