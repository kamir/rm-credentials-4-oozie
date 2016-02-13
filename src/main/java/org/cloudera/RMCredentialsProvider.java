package org.cloudera;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
   
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.security.token.delegation.DelegationTokenIdentifier;

import org.apache.hadoop.security.token.Token;
import org.apache.oozie.ErrorCode;
import org.apache.oozie.action.ActionExecutor.Context;
import org.apache.oozie.action.hadoop.Credentials;
import org.apache.oozie.action.hadoop.CredentialsProperties;
import org.apache.oozie.util.XLog;


    
    

/**
 * Credentials implementation to store RM-DelegationToken in jobConf, provided to an action. 
 * 
 * User specifies these credential properties along with the action configuration
 * 
 * The jobConf is used further to pass credentials to the tasks while running
 * Oozie server should be configured to use this Credentials class by including 
 * it via property 'oozie.credentials.credentialclasses'.
 * 
 * User can extend the parent class to implement own class as well
 * for handling custom token-based credentials and add to the above server 
 * property
 */
public class RMCredentialsProvider extends Credentials {

    /* (non-Javadoc)
     * @see org.apache.oozie.action.hadoop.Credentials#addtoJobConf(org.apache.hadoop.mapred.JobConf, org.apache.oozie.action.hadoop.CredentialsProperties, org.apache.oozie.action.ActionExecutor.Context)
     */
    @Override
    public void addtoJobConf(JobConf jobconf, CredentialsProperties props, Context context) throws Exception {
        try {
            
            Token<DelegationTokenIdentifier> abctoken = new Token<DelegationTokenIdentifier>();
            jobconf.getCredentials().addToken(new Text("ABC Token"), abctoken);
            XLog.getLog(getClass()).debug("Added the ABC token in job conf");
        
        }
        catch (Exception e) {
            XLog.getLog(getClass()).warn("Exception in addtoJobConf", e);
            throw e;
        }
    }
 
 
}
