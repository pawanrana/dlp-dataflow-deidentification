/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.swarm.tokenization.avro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import org.apache.avro.generic.GenericRecord;
import org.junit.Test;

import static com.google.swarm.tokenization.avro.AvroReaderSplitDoFnTest.generateGenericRecords;

public class GenericRecordCoderTest {

    @Test
    public void testGenericRecordCoder() throws IOException {
        GenericRecord record = generateGenericRecords(1).get(0);
        GenericRecordCoder coder = GenericRecordCoder.of();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        coder.encode(record, outputStream);
        byte[] bytes = outputStream.toByteArray();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        assertEquals(record, coder.decode(inputStream));
    }

}
