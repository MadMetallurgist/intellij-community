/*
 * Copyright 2000-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.application.options.codeStyle.arrangement;

import com.intellij.psi.codeStyle.arrangement.match.ArrangementEntryType;
import com.intellij.psi.codeStyle.arrangement.match.ArrangementModifier;
import com.intellij.psi.codeStyle.arrangement.model.ArrangementAtomMatchCondition;
import com.intellij.psi.codeStyle.arrangement.model.ArrangementMatchCondition;
import org.jetbrains.annotations.NotNull;

/**
 * Combines and encapsulates information about arrangement matcher rules representation (tree nodes) and
 * underlying {@link ArrangementMatchCondition data model}.
 * <p/>
 * Implementations of this interface are not obliged to be thread-safe.
 * 
 * @author Denis Zhdanov
 * @since 8/15/12 1:04 PM
 */
public interface ArrangementRuleEditingModel {
  
  @NotNull
  ArrangementMatchCondition getMatchCondition();

  /**
   * Allows to answer if current model has a registered condition for the given key. A key is expected to be one of the standard
   * keys, e.g. {@link ArrangementEntryType type}, {@link ArrangementModifier modifier} etc. 
   * 
   * @param key  target key
   * @return     <code>true</code> if current model has a registered mapping for the given key;
   *             <code>false</code> otherwise
   */
  boolean hasCondition(@NotNull Object key);
  
  void addAndCondition(@NotNull ArrangementAtomMatchCondition setting);

  void removeAndCondition(@NotNull ArrangementMatchCondition node);
}
