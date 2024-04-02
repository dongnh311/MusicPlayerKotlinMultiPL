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
    
    static let shared = IOsMusicPlayerSingleTon()
    
    var firebaseFCMHelper: FirebaseFCMHelper = FirebaseFCMHelper()
    
    init() {
        firebaseFCMHelper.loadFCMToken()
    }

    func initObjectSendBack() {
        FirebaseLoginShare_iosKt.configSingletonForIos(musicPlayerSingleTonIOs: IOsMusicPlayerSingleTon.shared)
    }

}
