/**
 * Copyright 2013 TouK
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

package grails.plugins.httplogger.filters;

import grails.plugins.httplogger.MultiReadHttpServletRequest;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Tomasz Kalkosiński <tomasz.kalkosinski@gmail.com>
 */
public class LogRawRequestInfoFilter extends HttpLoggerFilter {

    private static final AtomicLong REQUEST_COUNTER = new AtomicLong();

    @Override
    protected void logRequest(MultiReadHttpServletRequest requestWrapper) throws IOException, ServletException {
        RequestData requestData = new RequestData(requestWrapper);
        requestData.setStartTimeMillis(System.currentTimeMillis());
        requestData.setRequestNumber(REQUEST_COUNTER.incrementAndGet());
        getHttpLogger().logBeforeRequest(requestWrapper, requestData);
    }

}
