import SwiftUI
import FirebaseCore
import FirebaseMessaging
import ComposeApp

@main
struct iOSApp: App {
    // Make delegate
    @UIApplicationDelegateAdaptor(IOsAppDelegate.self) var delegate
    
    init() {
        FirebaseApp.configure()
        //IOsMusicPlayerSingleTon.shared
        IOsMusicPlayerSingleTon.shared.initObjectSendBack()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
