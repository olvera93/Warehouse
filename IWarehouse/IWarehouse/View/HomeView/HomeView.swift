//
//  HomeView.swift
//  IWarehouse
//
//  Created by Gonzalo Olvera Monroy on 13/06/24.
//

import SwiftUI

struct HomeView: View {
    var body: some View {
        
        let myFont = Font
               .system(size: 20)
               .monospaced()
        
        VStack {
            LottieView(animationFileName: "ecommerce", loopMode: .loop)
                .frame(width: 300, height: 330)
            
            Text("Welcome to The Warehouse")
                .font(myFont)
                .bold()
                .padding(16)
            
            
        }
    }
    
}


#Preview {
    HomeView()
}
