//
//  IOsAppDelegate.swift
//  iosApp
//
//  Created by Nguyen Hoai Dong on 15/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Foundation
import FirebaseCore
import FirebaseMessaging
import FirebaseFirestore
import FirebaseAuth

class IOsAppDelegate: NSObject, UIApplicationDelegate {
      func application(_ application: UIApplication,
                       didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
          print("FirebaseApp.configure()")
          FirebaseApp.configure() //important
          
        return true
      }
        

      func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
            Messaging.messaging().apnsToken = deviceToken
            print("FCM registration apnsToken: \(deviceToken)")
      }
}
