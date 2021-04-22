package com.carnnjoh.poedatatool.services;

import java.util.Arrays;

public class QueryResponseListingResultToUrlConverter {

    public static void main(String[] args) {

        String[] ids = new String[]{
                "0898489bc62abaa69f33f4086dbd77c24bd5f00a2aea9d7ed14e99ce6dbb3617",
                "f6ef890c451dccf79d418c05d493077b187f60b656e7a1538afce063970aed5d",
                "377415b1eb415da8d0a533fe590c6a607b4c42bae6bfcb6dd8f06ab9d59fb038",
                "d97e6a8a8122d9b0ee49fcf56aa80d4f88eb4ca21d1a226cc57d06a39e02444a",
                "48892de4b9a01b9823939d5ac55d30444a85cfe731bbf67e630b1bea5eaeb0c9",
                "87bb96eb52e3f40843fb68a5a53ce36c6e957c166cf702074769edf43d2853d3",
                "022822f5b7dbddaf6275b0acbf72b993f94c28c6ba23f60a862d93a3bd5d894e",
                "b95b3088975487f902243f8906864d437a2c014785a7f1cf8d790ae35da10dd7",
                "e354084a7dada693dccb5540ba0fc3a79b550e111823be3318e0d1f3a0631706",
                "bc0e38bd4494130ba12c10e47b5cf7b61c75bc3cf238a71fade62e9da58fa7da",
                "68fad4c256dc601f74042a9a150659c62f7353e1683e0fe000123ffe81732e23",
                "91dd5a5a41796dd0b320a8c578b758dc46d1d9166a8ec2ae06277dc18425b398",
                "7e6ff6ef234b7d17d31f48c2bf55934878923921371a36d4fe6abb24a7575d00",
                "e3f9fa48cc8ad9c3e9d138c786b892e408321df335aecabfa2ee7c43e279965e",
                "84f0eaad13ec260affb53d3f38f57fb4899e2e51eb10872e25f4dc64231a4540",
                "162eb499ed69e10464e3dcefc94991c7c1bb0712c88d13003a30bf6005052511",
                "ed6fa7d72b86e57fa1f1cb6e44a70fb50562a02530c3d5700de54ad173eb7062",
                "c5c0649128c0cef234a6dc83ac91497ad611b156fc908cba406103cae7c0706a",
                "822b0fb1ce10cddc8c4de5fe0b3bb5042b63c4e6a32b70d2061708911db4dc44",
                "c8d5d31d34d23a2373f8d957a0040cb694b8e4fe8017d42ec4ba9fc78c1bb7d6",
                "aa528d55c80cb7735a47d08fd9d971d8ec4de6a46545461c94e5c5e24bc90421",
                "b6a0b79dfbcfce2d7ff50980ad84e8c6f0a1c1eaa1df82703de82442a1aa26c4",
                "dab113bc90c6d70250937eb2e161ab96758f601350fd0e1cb215dc095b06d6e3",
                "a1f3e71db43455a6c6fd9911877b4775fcf3daa4380c23fabd8e90fc5d41f3f8",
                "1fbbe3f40973dae76d664111542282c17f36a24891c0c2fd729703fb6586e5ca",
                "10e1e1cd1db42fccfe7f35ab9fa632971f38868b684f0070983ddfaecf915619",
                "98f7f44852bd7fad5b0ee3546568ddc3eb712a2a8a5e496259d6fd02d2026e39",
                "17a1c233a92f543ca8d215aadb464b33a6d59c16c9d59c516c5193df32d96818",
                "ec049ce26fb6545e39a3f903a7ea4d9ecdfbf17bb81dce4aa03b9e3dd95e0269",
                "10df58fd1d5be4093ae6116d525612fdc13990f40ca7486ce9daaf83740490ce",
                "a52171047c147acc33a79a25438444e045106f3d6a905fdd8bf024c4475bae09",
                "76d68ceebc8125ba8f01a1af051f5ce9cbeaf04d44bed71830166508992501b8",
                "edd7da13c482bd8709d46035403e6089d041e849ed537d0cb8334e3ef287ea2c",
                "fb518bb73d6df3b283ac9d9f25ee64c81904ae4d7d45f9ea7a0aa866ad3a830e",
                "f6503a1563606edca8766977edc0276268570274a78dbf11eba72241750f3335",
                "306aaefe1e03d01ea10cddefe0fcc43900e328f4349ceaabbd7df806b2aeaf1b",
                "3de825ed7c9bb633ebdf26c0d62102cc3af9a72db77f84ca4dff3b3ab40279d3",
                "afc1625ce1c776b546c9483cf7e4a5e3c5ec04df1517601959e2ce2f2e49de8f",
                "bcc9e5f710c376ddd501a144213b4bf317bfe851ec4bae8a9353167dc799935f",
                "003d8de6aa2ee427ae2fb9abb4fa501e978559aacaa1dec5d563cd35de42cb7a",
                "4bed6e136e90309a47412cac76e7c61835bc04958f0ac7f4778d4d0d1d8169e8",
                "f7c0ca625e480e28261d2b91af18d892c2af10425f69cef3bc550abf2f7e2091",
                "792bafde8a89cbadb425fd20f08f4820c77d036839823c750afa9df5ddbc4eb9",
                "977e8173040a0481dbb5035c96e98be204fc1cc8056b9ecbaf644176b390d8d3",
                "37a570d37b7186c47b84d01c3b69877d6f8b339f05e013b4d86b2d5c7437acac",
                "a7d28705e9ab48ba16cee778ea57716ed4a863aac27386b27867d3bc704458dc",
                "53f9d7b4dfcff8965cb067c94aaa8ef1932b3e5f3e6f82a94595eaaaa290a484",
                "0ba4bcbe91fbc45cd3f608fa8d259de566c0eb93f9ee889864671028c95019bf",
                "251570831b60ab0ece6c7e26659b2e6d3093237e1a73ba0fc596a607fa40cc7f",
                "2e7b267edeb7d68bb250be069fe80801069451b15d5b6172137f03ce2e282ef1",
                "1beb044b95137583d90e591fd363527efe5d68e96999f18ffcba87d9da4a8a64",
                "ffaba67b23384847169ed75680245915ad2555511a7205543ff25fe38d89c056",
                "b5239cf40bd3e15a1416f869252ebeafa8cc4a09bed1e5669263dc0fb6f33912",
                "284a547012e2905577c0286079898d5e932cc72eae08f0e86e81f71c3200fdd8",
                "47a3ccbaaf7e1649f67a6f6d03159dbc6c3fad3c014cee331609f4aa74712ee5",
                "a9fca14777ff856492f0443a13373ca93d77674359386998640df1ba8b507e1b",
                "d559ed862a3d0ed68b01c2115dda1caf0970c0e00651ac3a59ad9cab8721849e",
                "1256c1e5c2c031e33906abf38a9b400b059b7a3ec34dd7717da6f7bb657796e5",
                "783953cae32c2814a92aa03f70350b8717153a6bc4c93769581d423da34e33b5",
                "f38cf75c4476aeb0ee8f726524ab00e849dd4ae4217617556dedf9359275dbad",
                "bea187ae483e72273f0ff0b9e371607dc8694dd94a9fdfa9981794de4ccb35d1",
                "2060a7d79b464011364dab79289cb3530c5457c2df6807ce93af4c69d42b8838",
                "8095e130231db2126d0d457ca2a9669d863bdd1fb2cd06895db77579167e2d2a",
                "5c348097d970d7674d8332e9af911468dd35effd939d3821fe0bda8d57afc7e5",
                "1748321415a9ce5b694a3d5a43e6e3e0d36ed5024de5a1631a90bed6c6388f21",
                "e84aef858ce531150e77d1da699fb7315ca01598181e45c5b62da92428b31b0c",
                "5ea2423ce6eb61d9f2bbecd75884b0ca6ade18c691f0a27b8b246f2475f8e08a",
                "463a86a700877eab1567e90cbbb9813a99aa52107877f1244c2513a6c19b382e",
                "f0248e8e3f11b18d1f9171eb79e40ae12b4e9ee9218ff32140d09a12c1c7118d",
                "616ca54ef8e09d2dc17fa61b7b55f798180a9bd494139df66d401abf53ef8707",
                "9b767da337d3a2ea7248bb1d2bd95a3e4d08aff6d33cd78fd6443cc4cb34746c",
                "2b9adab405bde71bb207a0e539bbf247acd39586194e4f73ee2fca3e11f80495",
                "838617e851aa44c96dfb17423c7336bf70427ddb2c6ba191e363e6de2171ab41",
                "4a6e2d88a7a68b395461323fcc584c43139ea3584b99c747b5094814003eacf6",
                "5684ae677e882386eade750214ba8793c0c306c39628e2867afcb4c54ddb9c78",
                "61eaeb0bde71058452c65ee25505ac7db72ec6fd027f164067b655208844d421",
                "9acf11891b6093ce35d4331702ebe518e77a99fcc19bf0a9595328d684f02cb7",
                "b1c4a2bafb5d105b8ee2334123d1bff1e73a3bf904889d6a181f537beeefe9ad",
                "6d1edd377d66c0a723499fc1f02ed97e05de0059dfb3ce62dcc71ee4f0194784",
                "d524be680e1fb5bfc0d142590db249cdf2bfc01390dd0055c62276d9905f4289",
                "f96b006ba5b0719e73aab2ae7548197807f64e2f2a36dde741bcbe4fdbc6bef1",
                "c1f7d74f955e69b60880119533bfb84cc1835e3067fd6549bc20513a53b492c2",
                "b41ae928e08c8fbef663205b5e210f347e7aeca0424c8f5ec61e7febc87d0bb2",
                "63a676abd89a98c699af982f088ada8a7a0d5354d06dbce918e98ca7d24b25e2",
                "388151994b20b454db1659d6e9d8a47757630cde2f6f259c9019d1de5595b018",
                "243f26efeb7092376d682cd8f74c63ee7291ca36801b91aa0bcd67400a3f7e31",
                "919982521377c16973e9cd3b03cec8940aaa7c12e7a4bac62fe35bb5ff404fe3",
                "7bbafaf8b20b5df174d7306c537bcb95a8783266f390af15d268eb3b37c5610d",
                "b8c6dba1ef636ae05adc11efa819b293f58c2ae23bf577269518ee8079c82d78",
                "22d4444389c46514ee787c789d395ac9732156378e06a54062341e7f89ed18fd",
                "39158d5726839cd020dbb1fe0d0835938d6fb9b3834f883dcd46537c0ff25a01",
                "95e2b9a41ca132dce0c69f2706122ada2e78f4c9e5abed738a2debf4b3ca4244",
                "e4f0b0ef9a2192fdff194403a6a4e8740b6d9cfd043d90ad8742431426780532",
                "f195009c118fb3acbff6ce4d30e98664e3c72fc326ee5344404ea9708cfb2580",
                "a67f661fc5fc00f4f7ac6bb57be7f8ace397030c245b394841aa1b90d946cf4d",
                "c3592b999129aad77ea9f73bb366aa1e1566870865e655c95a607d3e9ba4aada",
                "345286b31bd4f51cfcb59d4ea6307dd40a81e3accf10b7dcd361f6ce99c379ab",
                "b30011032ad75f5b36d0624608e4cdbd345d88e906b7011b4d97afb782dbf9d9",
                "cbd98fc1d6d5bf8f950f56eb8c51ae8b54de9fd7049990a2438d1402ec56767b",
                "b379f9c26ae3778bb43270d4cb648b59bf012390f481e9dbbac4633544035e72"
        };

        String HTTP_BASE = "http://www.pathofexile.com/api/trade/fetch/";
        String httpQuerySeparator = "?query=";

        String query = "LK2j22Xfn";


        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append(HTTP_BASE);

        for(String id : ids) {
            stringBuilder.append(",").append(id);
        }
        stringBuilder.append(httpQuerySeparator).append(query);

        System.out.println(stringBuilder.toString());

    }


}
