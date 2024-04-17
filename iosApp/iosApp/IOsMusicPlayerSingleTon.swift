//
//  MusicPlayerSingleTon.swift
//  iosApp
//
//  Created by Nguyen Hoai Dong on 02/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import ComposeApp

class IOsMusicPlayerSingleTon: MusicPlayerSingleTonIOs {
    
    // Make singleton item share
    static let shared = IOsMusicPlayerSingleTon()
    
    // Firebase FCM helper
    var firebaseFCMHelper: FirebaseFCMHelper = FirebaseFCMHelper()
    
    // Save app deleagte
    var appDelegate: IOsAppDelegate? = nil
    
    // Login
    var firebaseLoginIos: FirebaseGoogleControl = FirebaseGoogleControl()
    
    
    init() {
        firebaseFCMHelper.loadFCMToken()
    }
    
    /**
     * Init app detgate for handle if need.
     */
    func initAppDelegate(iosAppDelegate: IOsAppDelegate) {
        self.appDelegate = iosAppDelegate
    }

    func initObjectSendBack() {
        FirebaseLoginShare_iosKt.configFirebaseLoginIos(input: firebaseLoginIos)
        FirebaseLoginShare_iosKt.configSingletonForIos(musicPlayerSingleTonIOs: IOsMusicPlayerSingleTon.shared)
    }
    
    func loadTextForTest() -> String {
        return "IOs Text"
    }
    
    
}
