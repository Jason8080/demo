package com.gm.demo.fabric.sdk.ca.org;

import com.gm.demo.fabric.sdk.Fabric;
import com.gm.demo.fabric.sdk.ca.sample.SampleOrg;
import com.gm.help.base.Quick;
import com.gm.utils.base.Prop;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 组织管理.
 *
 * @author Jason
 */
public class Org {

    private static final String PROPBASE = "org.hyperledger.fabric.sdktest.";

    private static final String INTEGRATIONTESTS_ORG = PROPBASE + "integrationTests.org.";

    private static final Pattern orgPat = Pattern.compile("^" + Pattern.quote(INTEGRATIONTESTS_ORG) + "([^\\.]+)\\.mspid$");

    private static final String INTEGRATIONTESTSTLS = PROPBASE + "integrationtests.tls";

    public static final HashMap<String, SampleOrg> sampleOrgs = new HashMap();

    /**
     * 装载Org.
     */
    public static HashMap<String, SampleOrg> mount() {
        for (Map.Entry<Object, Object> x : Prop.load(Fabric.NET_PROPERTIES).entrySet()) {
            final String key = x.getKey() + "";
            final String val = x.getValue() + "";
            if (key.startsWith(INTEGRATIONTESTS_ORG)) {

                Matcher match = orgPat.matcher(key);

                if (match.matches() && match.groupCount() == 1) {

                    String orgName = match.group(1).trim();

                    final SampleOrg sampleOrg = new SampleOrg(orgName, val.trim());

                    String domain = Prop.getProperty(INTEGRATIONTESTS_ORG + orgName + ".domname");

                    sampleOrg.setDomainName(domain);

                    // 这里直接初始化了Ca客户端
                    Quick.run(() -> {

                        sampleOrg.setCALocation(httpTLSify(Prop.get(Fabric.NET_PROPERTIES).getProperty((INTEGRATIONTESTS_ORG + sampleOrg.getName() + ".ca_location"))));

                        final HFCAClient ca = HFCAClient.createNewInstance(sampleOrg.getCALocation(), sampleOrg.getCAProperties());

                        ca.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());

                        sampleOrg.setCAClient(ca);

                    });

                    sampleOrgs.put(orgName, sampleOrg);
                }
            }
        }
        return sampleOrgs;
    }


    private static String httpTLSify(String location) {
        location = location.trim();

        return Prop.is(INTEGRATIONTESTSTLS) ?
                location.replaceFirst("^http://", "https://") : location;
    }
}
