package com.company.aviation.security;

import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "UI: minimal access", code = UiMinimalRole.CODE)
public interface UiMinimalRole {

    String CODE = "ui-minimal";

    @EntityPolicy(entityName = "*", actions = {EntityPolicyAction.READ})
    @EntityAttributePolicy(entityName = "*", attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @ViewPolicy(viewIds = "aviation_*")
    @MenuPolicy(menuIds = "*")
    @SpecificPolicy(resources = "ui.loginToUi")
    void uiMinimal();
}
