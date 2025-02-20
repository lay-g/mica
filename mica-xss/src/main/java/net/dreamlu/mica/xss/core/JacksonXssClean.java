/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dreamlu.mica.xss.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.xss.config.MicaXssProperties;
import net.dreamlu.mica.xss.utils.XssUtil;

import java.io.IOException;

/**
 * jackson xss 处理
 *
 * @author L.cm
 */
@Slf4j
@RequiredArgsConstructor
public class JacksonXssClean extends XssCleanDeserializerBase {
	private final MicaXssProperties properties;
	private final XssCleaner xssCleaner;

	@Override
	public String clean(String text) throws IOException {
		if (XssHolder.isEnabled()) {
			String value = xssCleaner.clean(XssUtil.trim(text, properties.isTrimText()));
			log.debug("Json property value:{} cleaned up by mica-xss, current value is:{}.", text, value);
			return value;
		} else {
			return XssUtil.trim(text, properties.isTrimText());
		}
	}

}
