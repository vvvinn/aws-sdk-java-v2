/*
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.enhanced.dynamodb.model;

import java.util.Map;
import software.amazon.awssdk.annotations.Immutable;
import software.amazon.awssdk.annotations.NotThreadSafe;
import software.amazon.awssdk.annotations.SdkPublicApi;
import software.amazon.awssdk.annotations.ThreadSafe;

/**
 * An interface shared by all types that have attributes.
 *
 * <p>
 * This allows sharing of attribute retrieval code and documentation between types with attributes.
 */
@SdkPublicApi
@ThreadSafe
@Immutable
public interface AttributeAware<AttributeT> {
    /**
     * Retrieve an unmodifiable view of all attributes, indexed by the attribute key.
     *
     * <p>
     * This call should never fail with an {@link Exception}.
     */
    Map<String, AttributeT> attributes();

    /**
     * Retrieve the attribute matching the provided attribute key, or null if no such attribute exists.
     *
     * <p>
     * Reasons this call may fail with a {@link RuntimeException}:
     * <ol>
     *     <li>The provided table name is null.</li>
     * </ol>
     */
    AttributeT attribute(String attributeKey);

    /**
     * An interface shared by all builders that have attributes.
     *
     * <p>
     * This allows sharing of attribute population code and documentation between types with attributes.
     */
    @NotThreadSafe
    interface Builder<AttributeT> {
        /**
         * Add all of the provided attributes, overriding any existing attributes that share the same keys.
         *
         * <p>
         * Reasons this call may fail with a {@link RuntimeException}:
         * <ol>
         *     <li>The provided map or one of its keys is null.</li>
         *     <li>If this or any other {@code attribute}-modifying method is called in parallel with this one.
         *     This method is not thread safe.</li>
         * </ol>
         */
        Builder<AttributeT> putAttributes(Map<String, ? extends AttributeT> attributeValues);

        /**
         * Add the requested attribute, overriding any existing attribute that shares the same key.
         *
         * <p>
         * Reasons this call may fail with a {@link RuntimeException}:
         * <ol>
         *     <li>The attribute key is null.</li>
         *     <li>If this or any other {@code attribute}-modifying method is called in parallel with this one.
         *     This method is not thread safe.</li>
         * </ol>
         */
        Builder<AttributeT> putAttribute(String attributeKey, AttributeT attributeValue);

        /**
         * Remove the attribute that matches the provided key. If no such attribute exists, this does nothing.
         *
         * <p>
         * Reasons this call may fail with a {@link RuntimeException}:
         * <ol>
         *     <li>The attribute key is null.</li>
         *     <li>If this or any other {@code attribute}-modifying method is called in parallel with this one.
         *     This method is not thread safe.</li>
         * </ol>
         */
        Builder<AttributeT> removeAttribute(String attributeKey);

        /**
         * Remove all attributes in this object, leaving the attributes empty.
         *
         * <p>
         * Reasons this call may fail with a {@link RuntimeException}:
         * <ol>
         *     <li>If this or any other {@code attribute}-modifying method is called in parallel with this one.
         *     This method is not thread safe.</li>
         * </ol>
         */
        Builder<AttributeT> clearAttributes();
    }
}