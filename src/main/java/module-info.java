/*
 *  Copyright 2014-present Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * Joda-Collect provides a {@code Grid} data structure based on the {@code Table} data structure in Guava.
 */
module org.joda.collect {

    // dependency on Guava - transient as Guava types are in Joda-Convert API
    requires transitive com.google.common;

    // export all packages
    exports org.joda.collect.grid;

}
