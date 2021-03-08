/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.testing.mock.osgi.testsvc.osgiserviceutil.activatedeactivate;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component
public class Service1Constructor {

    private boolean activated;
    private ComponentContext componentContext;

    @Activate
    public Service1Constructor(ComponentContext ctx) {
        this.activated = true;
        this.componentContext = ctx;
    }

    @Deactivate
    private void deactivate(ComponentContext ctx) {
        this.activated = false;
        this.componentContext = null;
    }

    public boolean isActivated() {
        return activated;
    }

    public ComponentContext getComponentContext() {
        return componentContext;
    }

}
