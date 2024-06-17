//
//  WareTextField.swift
//  IWarehouse
//
//  Created by Gonzalo Olvera Monroy on 13/06/24.
//

import SwiftUI

struct WareTextField: View {
    @State var value: String
    var placeholder: String
    var contentDescription: String
    var leadingIcon: String?
    var isPassword: Bool
    var isEnabled: Bool = true
    var keyboardType: UIKeyboardType
    var returnKeyType: UIReturnKeyType
    var backgroundColor: Color
    var tooltipText: String?
    var tooltipEnabled: Bool
    
    @State private var hidePassword = true
    @State private var isTooltipVisible = false
    
    
    var body: some View {
        HStack {
            if let leadingIcon = leadingIcon {
                Image(systemName: leadingIcon)
                    .foregroundColor(.primary)
            }
            
            if isPassword {
                if hidePassword {
                    SecureField(placeholder, text: $value)
                        .padding(16)
                        .cornerRadius(16)
                        .frame(maxWidth: .infinity)

                } else {
                    TextField(placeholder, text: $value)
                        .padding(16)
                        .cornerRadius(16)
                        .frame(maxWidth: .infinity)

                }
                                
                Button(action: {
                    hidePassword.toggle()
                }, label: {
                    Image(systemName: hidePassword ? "eye.slash" : "eye")
                        .foregroundColor(isEnabled ? .primary : .secondary)
                })
                .disabled(!isEnabled)
                
            } else {
                TextField(placeholder, text: $value)
                    .padding(16)
                    .frame(maxWidth: .infinity)

                if tooltipEnabled, let tooltipText = tooltipText {
                    Button(action: {
                        isTooltipVisible.toggle()
                    }) {
                        Image(systemName: "info.circle")
                            .foregroundColor(isEnabled ? .primary : .secondary)
                    }
                    .disabled(!isEnabled)
                }
            }
        }
        .padding(8)
        .background(RoundedRectangle(cornerRadius: 16).fill(backgroundColor))
        .keyboardType(keyboardType)
        .alert(isPresented: $isTooltipVisible) {
            Alert(
                title: Text("Information"),
                message: Text(tooltipText ?? ""),
                dismissButton: .default(Text("OK"))
            )
        }
    }
}

struct WarePasswordTextField: View {
    @State var value: String
    var contentDescription: String
    var placeholder: String = "Password"
    var leadingIcon: String = "lock"
    var isEnabled: Bool = true
    var keyboardType: UIKeyboardType = .default
    var returnKeyType: UIReturnKeyType = .default
    var backgroundColor: Color =  Color.white.opacity(0.2)

    var body: some View {
        WareTextField(
            value: value,
            placeholder: placeholder,
            contentDescription: contentDescription,
            leadingIcon: leadingIcon,
            isPassword: true,
            isEnabled: isEnabled,
            keyboardType: keyboardType,
            returnKeyType: returnKeyType,
            backgroundColor: backgroundColor,
            tooltipEnabled: false
        )
    }
}


struct WareTextField_Previews: PreviewProvider {
    
    static var previews: some View {
        WareTextField(
            value: "Paco",
            placeholder: "Enter text",
            contentDescription: "Custom text field",
            leadingIcon: "person",
            isPassword: false,
            isEnabled: true,
            keyboardType: .default,
            returnKeyType: .done,
            backgroundColor: Color.gray.opacity(0.2),
            tooltipText: "This is a tooltip",
            tooltipEnabled: true
        )
        .padding()
    }
}

