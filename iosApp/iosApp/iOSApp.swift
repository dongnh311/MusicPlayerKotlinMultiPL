import SwiftUI
import FirebaseCore
import FirebaseMessaging
import ComposeApp

@main
struct iOSApp: App {
    init() {
        FirebaseApp.configure()
        IOsMusicPlayerSingleTon.shared
        IOsMusicPlayerSingleTon.shared.initObjectSendBack()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
