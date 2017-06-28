/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.uuf.internal.util;

import org.wso2.carbon.uuf.core.Component;
import org.wso2.carbon.uuf.core.Fragment;
import org.wso2.carbon.uuf.core.Layout;
import org.wso2.carbon.uuf.core.Page;
import org.wso2.carbon.uuf.core.Theme;
import org.wso2.carbon.uuf.internal.io.StaticResolver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriUtils {

    public static final String COMPONENT_STATIC_RESOURCES_URI_PREFIX = "/public/components";
    public static final String THEMES_STATIC_RESOURCES_URI_PREFIX = "/public/themes/";
    public static final String FRAGMENTS_URI_PREFIX = "/fragments/";
    private static final String URL_DOMAIN_REGEX = ".?(\\://)((?:www.)?([^\\/]+))";
    private static final String URL_PREFIX_REGEX = "^(http|https)://.*";

    public static String getPublicUri(Component component, Page page) {
        return COMPONENT_STATIC_RESOURCES_URI_PREFIX + component.getContextPath() + "/" +
                StaticResolver.DIR_NAME_COMPONENT_RESOURCES;
    }

    public static String getPublicUri(Component component, Layout layout) {
        return COMPONENT_STATIC_RESOURCES_URI_PREFIX + component.getContextPath() + "/" +
                StaticResolver.DIR_NAME_COMPONENT_RESOURCES;
    }

    public static String getPublicUri(Component component, Fragment fragment) {
        return COMPONENT_STATIC_RESOURCES_URI_PREFIX + component.getContextPath() + "/" + fragment.getSimpleName();
    }

    public static String getPublicUri(Theme theme) {
        return THEMES_STATIC_RESOURCES_URI_PREFIX + theme.getName();
    }

    public String getDomain(String url){
        Pattern pattern = Pattern.compile(URL_DOMAIN_REGEX);
        if(!url.matches(URL_PREFIX_REGEX)){
            throw new IllegalArgumentException("URL needs to be an absolute URL starting with http or https ");
        }
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(2);
        } else {
            throw new IllegalArgumentException("Invalid URL"+ url);
        }
    }
}
