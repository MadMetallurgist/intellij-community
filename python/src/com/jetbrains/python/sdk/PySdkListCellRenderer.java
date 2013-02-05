package com.jetbrains.python.sdk;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.SdkType;
import com.intellij.ui.LayeredIcon;
import com.intellij.ui.ListCellRendererWrapper;
import com.jetbrains.python.sdk.flavors.PythonSdkFlavor;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class PySdkListCellRenderer extends ListCellRendererWrapper<Sdk> {
  private final Map<Sdk, SdkModificator> mySdkModifiers;

  public PySdkListCellRenderer(@Nullable Map<Sdk, SdkModificator> sdkModifiers) {
    mySdkModifiers = sdkModifiers;
  }

  @Override
  public void customize(JList list, Sdk sdk, int index, boolean selected, boolean hasFocus) {
    if (sdk != null) {
      final PythonSdkFlavor flavor = PythonSdkFlavor.getPlatformIndependentFlavor(sdk.getHomePath());
      final Icon icon = flavor != null ? flavor.getIcon() : ((SdkType)sdk.getSdkType()).getIcon();

      final String name;
      if (mySdkModifiers != null && mySdkModifiers.containsKey(sdk)) {
        name = mySdkModifiers.get(sdk).getName();
      }
      else {
        name = sdk.getName();
      }

      if (PythonSdkType.isInvalid(sdk)) {
        setText("[invalid] " + name);
        setIcon(wrapIconWithWarningDecorator(icon));
      }
      else if (PythonSdkType.isIncompleteRemote(sdk)) {
        setText("[incomplete] " + name);
        setIcon(wrapIconWithWarningDecorator(icon));
      }
      else {
        setText(name);
        setIcon(icon);
      }
    }
  }

  private LayeredIcon wrapIconWithWarningDecorator(Icon icon) {
    final LayeredIcon layered = new LayeredIcon(2);
    layered.setIcon(icon, 0);
    // TODO: Create a separate invalid SDK overlay icon (DSGN-497)
    final Icon overlay = AllIcons.Actions.Cancel;
    layered.setIcon(overlay, 1);
    return layered;
  }
}
