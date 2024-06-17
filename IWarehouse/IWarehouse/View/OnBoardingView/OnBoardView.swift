//
//  OnBoardView.swift
//  IWarehouse
//
//  Created by Gonzalo Olvera Monroy on 13/06/24.
//

import SwiftUI
import Lottie

struct OnBoardView: View {
    @State private var currentPage = 0
    @State private var navigateToNextView = false
    @State private var value: String = ""
    
    
    var body: some View {
        NavigationStack {
            VStack {
                TabView(selection: $currentPage) {
                    ContentOnBoard(lottieFile: "ecommerce", title: "Scribe", description: "fmspofmsopdfmdsfs")
                        .tag(0)
                    
                    ContentOnBoard(lottieFile: "ecommerce", title: "Colors", description: "fmspofmsopdfmdsfs")
                        .tag(1)
                    
                    ContentOnBoard(lottieFile: "ecommerce", title: "Pickers", description: "fmspofmsopdfmdsfs")
                        .tag(2)
                }
                .tabViewStyle(.page(indexDisplayMode: .always))
                .indexViewStyle(.page(backgroundDisplayMode: .always))
                
                if currentPage == 2 {
                    NavigationStack {
                        NavigationLink(destination: HomeView(value: value)) {
                            Button(action: {
                                navigateToNextView = true
                            }) {
                                Image(systemName: "arrow.right.circle.fill")
                                    .resizable()
                                    .frame(width: 50, height: 50)
                                    .foregroundColor(.teal)
                            }
                            .padding(.top, 20)
                        }
                    }.navigationDestination(isPresented: $navigateToNextView, destination: {HomeView(value: value)})
                }
            }
        }
    }
}

struct ContentOnBoard: View {
    var lottieFile: String
    var title: String
    var description: String
    
    var body: some View {
        VStack(spacing: 20) {
            
            LottieView(animationFileName: lottieFile, loopMode: .loop)
                .frame(width: 300, height: 300)
            
            Text(title)
                .font(.title)
                .bold()
            
            Text(description)
                .multilineTextAlignment(.center)
                .foregroundColor(.secondary)
        }
        .padding(.horizontal, 40)
    }
}

#Preview {
    OnBoardView()
}

