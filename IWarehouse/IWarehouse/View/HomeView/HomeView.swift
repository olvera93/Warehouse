//
//  HomeView.swift
//  IWarehouse
//
//  Created by Gonzalo Olvera Monroy on 13/06/24.
//

import SwiftUI

struct HomeView: View {
    
    @State var value: String
    
    
    var body: some View {
        
        let myFont = Font
            .system(size: 20)
            .monospaced()
        
        VStack {
            
            LottieView(animationFileName: "ecommerce", loopMode: .loop)
                .frame(width: 300, height: 330)
            Spacer()
            
            Text("Welcome to The Warehouse")
                .font(myFont)
                .bold()
                .padding(8)
            Spacer()
            
            WareTextField(
                value: value,
                placeholder: "Enter your username",
                contentDescription: "username",
                leadingIcon: "person",
                isPassword: false,
                isEnabled: true,
                keyboardType: .default,
                returnKeyType: .done,
                backgroundColor: Color.white.opacity(0.2),
                tooltipText: "This is a tooltip",
                tooltipEnabled: true
            )
            WarePasswordTextField(
                value: value,
                contentDescription: "Password"
            )
            Spacer()
            NavigationStack {
                NavigationLink(destination: ProductView()) {
                    Text("Login")
                        .frame(maxWidth: .infinity)
                        .padding(8)
                        .bold()
                        .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                        .foregroundColor(.white)
                        .background(.blue)
                        .cornerRadius(10)
                }
            }
            
            Spacer()
            
            Text("Forgot your password?")
                .font(.callout)
                .onTapGesture {
                    print("Hello text")
                }
            
            Spacer()
            
            HStack {
                Text("If you don't have an account,")
                    .bold()
                
                Text("create one")
                    .bold()
                    .foregroundStyle(.red)
            }
            .onTapGesture {
                print("Hello account")
                
            }
            
            
        }
        .padding(16)
        .navigationBarBackButtonHidden()
        
    }
    
}


#Preview {
    
    HomeView(value: "Helo")
}
