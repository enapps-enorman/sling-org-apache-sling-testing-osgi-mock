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
package org.apache.sling.testing.mock.osgi.testsvc.osgiserviceutil.bindunbind;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.sling.testing.mock.osgi.testsvc.osgiserviceutil.ServiceInterface1;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

@Component(reference = {
    @Reference(name = "reference1", bind = "bindReference1", unbind = "unbindReference1", service = ServiceInterface1.class,
            cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
})
public class Service5 {

    private List<ServiceInterface1> instances = new ArrayList<>();
    private List<ServiceReference<ServiceInterface1>> references = new ArrayList<>();
    private List<Map<String,Object>> configs = new ArrayList<>();

    void bindReference1(ServiceInterface1 instance, ServiceReference<ServiceInterface1> reference,
            ComponentServiceObjects<ServiceInterface1> serviceObjects, Map<String,Object> config) {
        assert serviceObjects.getService() == instance;
        assert serviceObjects.getServiceReference() == reference;
        instances.add(instance);
        references.add(reference);
        configs.add(config);
    }

    void unbindReference1(ComponentServiceObjects<ServiceInterface1> serviceObjects, Map<String,Object> config,
            ServiceInterface1 instance, ServiceReference<ServiceInterface1> reference) {
        assert serviceObjects.getService() == instance;
        assert serviceObjects.getServiceReference() == reference;
        instances.remove(instance);
        references.remove(reference);
        configs.remove(config);
    }

    public List<ServiceInterface1> getInstances() {
        return instances;
    }

    public List<ServiceReference<ServiceInterface1>> getReferences() {
        return references;
    }

    public List<Map<String,Object>> getConfigs() {
        return configs;
    }

}
