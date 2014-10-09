/*
 * Copyright 2013-2014 eBay Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kylinolap.common.util;

import java.util.Arrays;

/*
 * An array with correct equals(), hashCode(), compareTo() and toString()
 */
public class Array<T> implements Comparable<Array<T>> {
    public T[] data;

    public Array(T[] data) {
        this.data = data;
    }

    public String toString() {
        return Arrays.toString(data);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Array) {
            return Arrays.equals(this.data, ((Array<?>) o).data);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public int compareTo(Array<T> other) {
        return compare(this.data, other.data, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> int compare(T[] a, T[] b, boolean[] ascending) {
        int r = 0;
        int n = Math.min(a.length, b.length);
        boolean asc = true;

        for (int i = 0; i < n; i++) {
            r = ((Comparable<T>) a[i]).compareTo(b[i]);
            if (r != 0) {
                asc = (ascending != null && ascending.length > i) ? ascending[i] : true;
                return asc ? r : -r;
            }
        }
        return a.length - b.length;
    }

}
