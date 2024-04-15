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
    func loadTextForTest() -> String {
        return "IOs Text"
    }
    
    // Make singleton item share
    static let shared = IOsMusicPlayerSingleTon()
    
    // Firebase FCM helper
    var firebaseFCMHelper: FirebaseFCMHelper = FirebaseFCMHelper()
    
    // Save app deleagte
    var appDelegate: IOsAppDelegate? = nil
    
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
        FirebaseLoginShare_iosKt.configSingletonForIos(musicPlayerSingleTonIOs: IOsMusicPlayerSingleTon.shared)
    }

}
