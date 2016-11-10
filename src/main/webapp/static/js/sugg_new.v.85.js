var server_url = "/", winLocalStorage = "undefined" != typeof window.localStorage;
(function () {
    var a = function (a) {
        var g = document.createElement("b");
        g.innerHTML = "\x3c!--[if IE " + a + "]><i></i><![endif]--\x3e";
        return 1 === g.getElementsByTagName("i").length
    };
    if ("undefined" == typeof JSON || !JSON || a(8))a(8) && (window.JSON = void 0), a = document.createElement("script"), a.src = "/js/comment/json2.v.1.0.js", document.getElementsByTagName("head")[0].appendChild(a)
})();
function sugTemplate() {
}
if (0 <= window.navigator.appName.toUpperCase().indexOf("MICROSOFT") && document.execCommand)try {
    document.execCommand("BackgroundImageCache", !1, !0)
} catch (a) {
}
sugTemplate.prototype.getSuggCdnImgLink = function (a) {
    if (!a)return "";
    if ("https:" != location.protocol.toLowerCase() || -1 == a.indexOf("http://") && -1 == a.indexOf("https://") || 0 == a.indexOf("https://") && -1 == a.indexOf(".store.sogou.com"))return a;
    var c = "https://img0" + (Math.abs(function (a) {
                var c = 0;
                if (0 == a.length)return c;
                for (var g, f = 0; f < a.length; f++)g = a.charCodeAt(f), c = (c << 5) - c + g, c &= c;
                return c
            }(a) % 4) + 1) + ".sogoucdn.com", g = /^\s*http:\/\/www\.sogou\.com/g, h = /^\s*http:\/\/img\d*\.sogoucdn\.com/g, k = /^\s*http:\/\/img\d*\.store\.sogou\.com/g, l = /^\s*http:\/\/imgstore\d*\.cdn\.sogou\.com/g, x = /^\s*http:\/\/cmc\.imgstore\.cdn\.sogou\.com/g, m = /^\s*http:\/\/pic\d*\.sogoucdn\.com/g, f = /^\s*https:\/\/img\d*\.store\.sogou\.com/g;
    return g.test(a) ? a.replace(g, "") : h.test(a) ? a.replace(h, c) : k.test(a) ? a.replace(k, c) : l.test(a) ? a.replace(l, c) : x.test(a) ? a.replace(x, c) : m.test(a) ? a.replace(m, c) : f.test(a) ? a.replace(f, c) : c + "/v2/thumb?t=2&url=" + encodeURIComponent(a) + "&appid=200580"
};
sugTemplate.prototype.vmap = {
    21: ".v.2",
    60: ".v.5",
    69: ".v.2",
    89: ".v.1",
    91: ".v.2",
    95: ".v.1",
    97: ".v.5",
    113: ".v.2",
    117: ".v.1",
    123: ".v.1",
    125: ".v.2",
    131: ".v.1",
    137: ".v.2",
    145: ".v.4",
    163: ".v.3",
    164: ".v.3",
    165: ".v.1",
    166: ".v.3",
    186: ".v.1",
    191: ".v.5",
    201: ".v.1",
    206: ".v.2",
    208: ".v.1",
    210: ".v.4",
    244: ".v.5",
    262: ".v.1",
    266: ".v.1",
    273: ".v.1",
    312: ".v.4",
    317: ".v.3",
    320: ".v.4",
    328: ".v.2",
    330: ".v.1",
    338: ".v.1",
    344: ".v.2",
    349: ".v.1",
    376: ".v.6",
    322: ".v.1",
    330: ".v.1",
    336: ".v.2",
    349: ".v.2",
    400: ".v.1",
    406: ".v.1",
    411: ".v.2",
    446: ".v.2",
    478: ".v.1",
    697: ".v.1",
    698: ".v.1",
    2040: ".v.2",
    2140: ".v.2",
    2185: ".v.1",
    2098: ".v.4",
    2226: ".v.1",
    3005: ".v.4",
    4871: ".v.1",
    10001: ".v.4",
    10002: ".v.4",
    10003: ".v.2",
    10004: ".v.3",
    10005: ".v.4",
    70065600: ".v.1",
    ad1: ".v.4"
};
sugTemplate.prototype.cutTitle = function (a, c) {
    var g = -1 != navigator.userAgent.indexOf("MSIE 6") && !window.opera, h = /^(.*?)(<b>(.*?)<\/b>)?(<span><\/span>)?$/i.exec(c), h = [h[1], h[3], h[4] ? h[4] : ""], k = h[0].length + (h[1] ? h[1].length : 0), l;
    g && (a.style.height = null);
    a.innerHTML = "\u6211";
    l = a.offsetHeight;
    for (a.innerHTML = c; a.offsetHeight > 5 * l / 4 && -1 == a.className.indexOf("sugad-off");)a.innerHTML = h[0].substring(0, k) + (k > h[0].length ? "<b>" + h[1].substring(0, k - h[0].length) + "...</b>" : "...") + h[2], k--;
    g && (a.style.height = "27px")
};
sugTemplate.prototype.cutAllTitle = function (a, c) {
    for (var g = 0; g < a.length; g++)this.cutTitle(a[g], c[g])
};
sugTemplate.prototype.revertAllTitle = function (a, c) {
    for (var g = 0; g < a.length; g++)a[g].innerHTML = c[g]
};
sugTemplate.prototype.len = function (a) {
    return a && a.replace ? a.replace(/\[\/?em\]/g, "").replace(/[^\x00-\xff]/g, "rr").length : ""
};
sugTemplate.prototype.cutLength = function (a, c, g, h) {
    g = g || "...";
    h = h || 3;
    if (this.len(a) > c) {
        do a = a.lastIndexOf("[em]") == a.length - 4 ? a.substring(0, a.length - 4) : a.lastIndexOf("[/em]") == a.length - 5 ? a.substring(0, a.length - 5) : a.substring(0, a.length - 1); while (a && this.len(a) + h > c);
        a.lastIndexOf("[/em]") < a.lastIndexOf("[em]") && (a = a.substring(0, a.lastIndexOf("[em]")) + a.substring(a.lastIndexOf("[em]") + 4));
        return a + g
    }
    return a
};
sugTemplate.prototype.$c = function (a, c, g) {
    a = document.createElement(a);
    c && c.appendChild(a);
    g && (a.className = g);
    return a
};
sugTemplate.prototype.$ = function (a) {
    return document.getElementById(a)
};
sugTemplate.prototype.parseXML = function (a) {
    window.DOMParser ? (tmp = new DOMParser, xml = tmp.parseFromString(a, "text/xml")) : (xml = new ActiveXObject("Microsoft.XMLDOM"), xml.async = "false", xml.loadXML(a));
    return xml.documentElement
};
sugTemplate.prototype.selectNodes = function (a, c) {
    for (var g = [a], h = c.split("/"), k = 0; k < h.length; k++) {
        for (var l = h[k], x = [], m = 0; m < g.length; m++)for (var f = 0; f < g[m].childNodes.length; f++)g[m].childNodes[f].nodeName == l && (x[x.length] = g[m].childNodes[f]);
        g = x;
        if (null == g)return []
    }
    return g
};
sugTemplate.prototype.buildTitle = function (a, c, g, h, k, l) {
    a = this.$c("h3", a, k || "sugtype");
    l = l || 44;
    a.innerHTML = ['<a id="sgtitle" onfocus="this.blur();" href="', g ? g + '" target="_blank' : "/sogou?ie=utf8&query=" + c.query, '">', this.cutLength(h ? h : decodeURIComponent(c.query), l), "</a>"].join("");
    return a
};
sugTemplate.prototype.buildMoreHint = function (a, c) {
    var g = this.$c("div", a, "morehint"), g = this.$c("a", g);
    g.innerHTML = "\u66f4\u591a\u76f8\u5173\u7ed3\u679c&gt;&gt;";
    g.href = "/web?query=" + c.query;
    g.target = "_blank";
    g.setAttribute("hideFocus", "hidefocus")
};
sugTemplate.prototype.markRed = function (a, c, g) {
    c = c || "<em>";
    g = g || "</em>";
    if (0 < a.indexOf(c))return a;
    0 <= a.indexOf("[em]") && (a = a.replace(/\[em\]/g, c).replace(/\[\/em\]/g, g));
    return a
};
sugTemplate.prototype.escape = function (a) {
    return a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
};
sugTemplate.prototype.cutRed = function (a) {
    return a.replace(/\[\/?em\]/g, "")
};
sugTemplate.prototype.buildVRTitle = function (a, c, g) {
    a = this.$c("a", this.$c("h3", a, "se_embed_title"));
    var h = c.title, h = this.cutLength(h, g), h = this.escape(h), h = this.cutRed(h);
    a.href = c.url;
    a.target = "_blank";
    a.title = this.cutRed(c.title);
    a.innerHTML = h;
    return a
};
sugTemplate.prototype.buildContent = function (a, c) {
    for (var g = this.$c("div", a, "querylist"), h = 0; 2 > h; h++) {
        var k = this.$c("a", g, "qitem");
        k.target = "_blank";
        k.href = c.docs[h].url;
        k.onfocus = function () {
            this.blur()
        };
        var l = this.$c("strong", k, "qtitle"), l = this.$c("span", l, "siteico");
        c.docs[h].favicon && (l.style.background = "url(" + c.docs[h].favicon + ") no-repeat scroll 6px 50% transparent");
        l.innerHTML = this.cutLength(c.docs[h].title, 54);
        c.docs[h].content && (this.$c("span", k, "qsummary").innerHTML = this.cutLength(this.escape(c.docs[h].content), 106));
        k = this.$c("span", k, "qcite");
        c.docs[h].url && 0 < c.docs[h].url.indexOf("://") ? k.innerHTML = decodeURIComponent(c.docs[h].url.split("://")[1].split("/")[0]) : k.innerHTML = "www.sogou.com"
    }
    this.buildMoreHint(a, c)
};
sugTemplate.prototype.build = function (a, c, g, h, k, l, x) {
    if (!c)return !1;
    for (var m = this, f = -1, F = 0; F < g.length; F++)if (g[F] == decodeURIComponent(c.query)) {
        f = F;
        break
    }
    m.pv(c.query, c.type || "-1", f, c.doc_num || 0, null, null, c.vrtype || "-1", m.abtestId, l, x);
    h[f] = c.type || "-1";
    if (!c.doc_num || !c.docs)return !1;
    a.onclick = function (a) {
        if (!(a && 0 != a.button || !a && 0 != window.event.button))try {
            a = a || window.event;
            for (var g = a.target ? a.target : a.srcElement, h; g && g.tagName;) {
                h = g.tagName.toUpperCase();
                "A" == h && m.pv(c.query, c.type, f, c.doc_num, g.id || "sgcontent", g.href, c.vrtype, m.abtestId, l, x);
                if ("DIV" == h)break;
                g = g.parentNode
            }
        } catch (k) {
        }
    };
    if (c.type && 100 != c.type) {
        if (1E4 == c.type)try {
            var I = this.parseXML(c.docs[0].xml).getAttribute("type");
            c.qaType = parseInt(I);
            c.type = 1E4 + c.qaType;
            c.qaTag = k[decodeURIComponent(c.query)].tupu_key
        } catch (na) {
        }
        if (!this["build" + c.type]) {
            if (("317" == c.type || "60" == c.type) && !m.curtime) {
                if (window.standardtime) {
                    var K = window.standardtime;
                    window.standardtime = function (a, c) {
                        m.curtime = a;
                        return K(a, c)
                    }
                } else window.standardtime = function (a, c) {
                    m.curtime = a
                };
                g = document.createElement("script");
                g.charset = "gb2312";
                g.src = "websearch/features/standardtimeadjust.jsp?a=" + Math.random();
                document.body.appendChild(g)
            }
            g = document.createElement("script");
            g.charset = "gb2312";
            g.src = server_url + "js/sugtemp/build" + c.type + (this.vmap[c.type] || "") + ".js";
            document.body.appendChild(g)
        }
        this.buildVR(a, c, 0)
    } else a.innerHTML = "", this.buildTitle(a, c), this.buildContent(a, c);
    return !0
};
sugTemplate.prototype.httpsImgUrlReplace = function (a) {
    if (!a)return "";
    if ("https:" != location.protocol.toLowerCase() || -1 == a.indexOf("http://") || 0 == a.indexOf("https://"))return a;
    var c = "https://img0" + (Math.abs(function (a) {
                var c = 0;
                if (0 == a.length)return c;
                for (var g, h = 0; h < a.length; h++)g = a.charCodeAt(h), c = (c << 5) - c + g, c &= c;
                return c
            }(a) % 4) + 1) + ".sogoucdn.com", g = /^\s*http:\/\/www\.sogou\.com/g, h = /^\s*http:\/\/img\d*\.sogoucdn\.com/g, k = /^\s*http:\/\/img\d*\.store\.sogou\.com/g, l = /^\s*http:\/\/imgstore\d*\.cdn\.sogou\.com/g, x = /^\s*http:\/\/cmc\.imgstore\.cdn\.sogou\.com/g, m = /^\s*http:\/\/pic\d*\.sogoucdn\.com/g;
    return g.test(a) ? a.replace(g, "") : h.test(a) ? a.replace(h, c) : k.test(a) ? a.replace(k, c) : l.test(a) ? a.replace(l, c) : x.test(a) ? a.replace(x, c) : m.test(a) ? a.replace(m, c) : c + "/v2/thumb?t=2&url=" + encodeURIComponent(a) + "&appid=200580"
};
sugTemplate.prototype.buildVR = function (a, c, g) {
    if ("function" == typeof this["build" + c.type])try {
        var h = c.docs, k = h[0], l = h[0].xml;
        l && ("https:" == location.protocol.toLowerCase() && (l = l.replace(/http:\/\/www\.sogou\.com/g, ""), l = l.replace(/http:\/\/img\d+\.sogoucdn\.com/g, "https://img.store.sogou.com"), l = l.replace(/http:\/\/img\d+\.store\.sogou\.com/g, "https://img.store.sogou.com"), l = l.replace(/http:\/\/imgstore\.cdn\.sogou\.com/g, "https://img.store.sogou.com")), l = this.parseXML(l));
        try {
            k.url = this.selectNodes(l, "url")[0].firstChild.nodeValue
        } catch (m) {
        }
        try {
            k.title = this.selectNodes(l, "title")[0].firstChild.nodeValue
        } catch (m) {
        }
        try {
            k.domain = k.url.split("://")[1].split("/")[0]
        } catch (m) {
        }
        a.innerHTML = "";
        this["build" + c.type](a, c, k, l)
    } catch (m) {
    } else if (10 >= g) {
        var x = this;
        setTimeout(function () {
            x.buildVR(a, c, g + 1)
        }, 30)
    } else a.innerHTML = "", this.buildTitle(a, c), this.buildContent(a, c)
};
sugTemplate.prototype.httpsReplace = function (a) {
    a = a.replace(/http:\/\/img\d+\.sogoucdn\.com/g, "https://img.store.sogou.com");
    a = a.replace(/http:\/\/img\d+\.store\.sogou\.com/g, "https://img.store.sogou.com");
    return a = a.replace(/http:\/\/imgstore\.cdn\.sogou\.com/g, "https://img.store.sogou.com")
};
sugTemplate.prototype.reg = /{{(.*?)}}/g;
sugTemplate.prototype.tongji = {
    suggDivReqCount: 0,
    suggDivShowCount: 0,
    suggHotShowCount: 0,
    suggHisShowCount: 0,
    sugHisClk: 0,
    sugVrWordCount: 0,
    totalSugCount: 0,
    totalCommonSugCount: 0,
    totalHistorySugCount: 0,
    inputEmptyHisDivCount: 0,
    inputEmptyHisWordCount: 0
};
sugTemplate.prototype.resetTongji = function () {
    this.tongji.suggDivReqCount = 0;
    this.tongji.suggDivShowCount = 0;
    this.tongji.suggHisShowCount = 0;
    this.tongji.sugHisClk = 0;
    this.tongji.sugVrWordCount = 0;
    this.tongji.totalSugCount = 0;
    this.tongji.totalCommonSugCount = 0;
    this.tongji.totalHistorySugCount = 0;
    this.tongji.suggHotShowCount = 0;
    this.tongji.inputEmptyHisDivCount = 0;
    this.tongji.inputEmptyHisWordCount = 0
};
sugTemplate.prototype.pv = function (a, c, g, h, k, l, x, m, f, F) {
    try {
        imgurl = ["https:" == location.protocol.toLowerCase() ? "https://pb.sogou.com/" : "http://pb.sogou.com/", k ? "cl.gif" : "pv.gif", "?uigs_productid=webgo"];
        f && (imgurl.push("&inputstr=" + encodeURIComponent(f)), imgurl.push("&istrlen=" + f.length));
        imgurl.push("&query=");
        imgurl.push(encodeURIComponent(a));
        imgurl.push("&type=");
        imgurl.push(c);
        x && imgurl.push("&vrtype=" + x);
        imgurl.push("&pos=");
        imgurl.push(g);
        imgurl.push("&num=");
        imgurl.push(h);
        if (k)imgurl.push("&uigs_cl="), imgurl.push(k), imgurl.push("&"), imgurl.push("href=" + encodeURIComponent(l)), imgurl.push("&s_req=" + this.tongji.suggDivReqCount), imgurl.push("&s_div_show=" + this.tongji.suggDivShowCount), imgurl.push("&s_h_show=" + this.tongji.suggHisShowCount), 0 < this.tongji.suggHisShowCount && imgurl.push("&s_h_clk=" + this.tongji.sugHisClk), imgurl.push("&s_vr_word=" + this.tongji.sugVrWordCount), imgurl.push("&s_word=" + this.tongji.totalSugCount), imgurl.push("&s_common_word=" + this.tongji.totalCommonSugCount), imgurl.push("&s_h_word=" + this.tongji.totalHistorySugCount), imgurl.push("&s_hot_word=" + this.tongji.suggHotShowCount), imgurl.push("&s_emptyinput_h_word=" + this.tongji.inputEmptyHisWordCount), imgurl.push("&s_emptyinput_h_div=" + this.tongji.inputEmptyHisDivCount), this.resetTongji(); else {
            if ("adtop" != c && this.lastpv == imgurl.join(""))return;
            this.lastpv = imgurl.join("")
        }
        imgurl.push("&abtestid=" + m);
        "undefined" != typeof puid && puid && imgurl.push("&puid=" + puid);
        imgurl.push("&suguuid=" + F);
        imgurl.push("&uigs_t=");
        imgurl.push((new Date).getTime());
        (new Image).src = imgurl.join("")
    } catch (I) {
    }
};
sugTemplate.prototype.sug_rows_pv = function (a, c, g) {
    try {
        var h = ["https:" == location.protocol.toLowerCase() ? "https://pb.sogou.com/" : "http://pb.sogou.com/", "pv.gif", "?uigs_productid=suggweb"];
        h.push("&abtestid=" + a);
        h.push("&suguuid=" + c);
        g && h.push(g);
        h.push("&uigs_t=");
        h.push((new Date).getTime());
        (new Image).src = h.join("")
    } catch (k) {
    }
};
sugTemplate.prototype.abtestId = function () {
    function a(a) {
        var g;
        g = location.search.split("abtestid=");
        g = 1 < g.length ? g[1].split("&")[0] : null;
        if (g)return g;
        g = document.cookie;
        a += "=";
        var h = g.indexOf("; " + a);
        if (-1 == h) {
            if (h = g.indexOf(a), 0 != h)return null
        } else h += 2;
        var k = document.cookie.indexOf(";", h);
        -1 == k && (k = g.length);
        return unescape(g.substring(h + a.length, k))
    }

    return a("ABTEST") && 0 < a("ABTEST").split("|").length ? a("ABTEST").split("|")[0] : ""
}();
sugTemplate.prototype.buildTemplate = function (a, c) {
    var g = this;
    return a.replace(this.reg, function (a, k) {
        k = k.split("@");
        var l = g.selectNodes(c, "" + k[0]);
        return 0 < l.length ? 1 < k.length ? l[0].getAttribute(k[1]) : l[0].firstChild.nodeValue.replace(/\ue40a/g, "").replace(/\ue40b/g, "") : ""
    })
};
function sogouSugg(a) {
    function c(b) {
        var a = document.cookie;
        b += "=";
        var e = a.indexOf("; " + b);
        if (-1 == e) {
            if (e = a.indexOf(b), 0 != e)return null
        } else e += 2;
        var d = document.cookie.indexOf(";", e);
        -1 == d && (d = a.length);
        return unescape(a.substring(e + b.length, d))
    }

    function g() {
        var b = Fa("abtestid");
        return b ? b : c("ABTEST") && 0 < c("ABTEST").split("|").length ? c("ABTEST").split("|")[0] : ""
    }

    function h(b, a, e) {
        if (b)return b.addEventListener ? b.addEventListener(a, e, !1) : b.attachEvent("on" + a, e)
    }

    function k(b, a) {
        for (var e = a.getElementsByTagName("*"), d = new RegExp("\\b" + b + "\\b"), n = [], c = 0; c < e.length; c++)d.test(e[c].className) && n.push(e[c]);
        return n
    }

    function l(b, a) {
        if (b) {
            var e = b.className;
            (new RegExp("\\b" + a + "\\b")).test(b.className) || (b.className = e + " " + a)
        }
    }

    function x(b, a) {
        var e = new Date, d = 1E3 * e.getTime() + Math.round(1E3 * Math.random()), n = "";
        "" != ca && (n = "sugg" + ca);
        d = [("https:" == location.protocol.toLowerCase() ? "https://pb.sogou.com/" : "http://pb.sogou.com/") + "pv.gif", "?uigs_productid=", encodeURIComponent(n), "&uigs_t=", d, "&w=", encodeURIComponent(r.value), "&k=", encodeURIComponent(w), "&s="];
        d.push(-1 != v ? "t" : "f");
        w && d.push("&istrlen=" + w.length);
        "del_his_item" == b || "clear_history" == b || "more_history" == b || "input_fast_drop" == b || "drop_by_his" == b ? (d.push("&act=" + b), d.push("&abtestid=" + Q), a && d.push("&suguuid=" + a)) : (-1 != v && (d.push("&stj0=" + da[v]), d.push("&stj1=" + L[v]), d.push("&stj2=" + R[v])), d.push("&hp=" + pa), d.push("&hp1=" + Ga), -1 != v && (d.push("&cline="), d.push(v)), "sb" == b && (d.push("&s_req=" + p.tongji.suggDivReqCount), d.push("&s_div_show=" + p.tongji.suggDivShowCount), d.push("&s_h_show=" + p.tongji.suggHisShowCount), 0 < p.tongji.suggHisShowCount && d.push("&s_h_clk=" + p.tongji.sugHisClk), d.push("&s_vr_word=" + p.tongji.sugVrWordCount), d.push("&s_word=" + p.tongji.totalSugCount), d.push("&s_common_word=" + p.tongji.totalCommonSugCount), d.push("&s_h_word=" + p.tongji.totalHistorySugCount), d.push("&s_hot_word=" + p.tongji.suggHotShowCount), d.push("&s_emptyinput_h_word=" + p.tongji.inputEmptyHisWordCount), d.push("&s_emptyinput_h_div=" + p.tongji.inputEmptyHisDivCount), p.resetTongji()), b && (d.push("&act="), d.push(encodeURIComponent(b))), d.push("&r=" + e.getSeconds()), d.push("&abtestid=" + encodeURIComponent(Q)), "undefined" != typeof puid && puid && d.push("&puid=" + puid), d.push("&uk=" + (qa ? 1 : 0)), d.push("&sbby=" + Ha));
        (new Image).src = d.join("")
    }

    function m() {
        if (A.getElementById(Ia)) {
            var b = S("link");
            b.setAttribute("rel", "stylesheet");
            b.setAttribute("type", "text/css");
            b.setAttribute("href", server_url + "sug/css/m3.min.v.7.css");
            A.getElementsByTagName("head")[0].appendChild(b);
            r = A.getElementById(Ia);
            for (y = r.parentNode; y && "form" != y.tagName.toLowerCase();)y = y.parentNode;
            y && (E.reset && y.reset(), r.setAttribute("autocomplete", "off"), h(r, "mousedown", f), h(r, "keydown", pb), J = r.value, I())
        } else setTimeout(m, 50)
    }

    function f() {
        ea && Ja();
        E.oms && (Ka = J = "");
        M = !1
    }

    function F() {
        var b, a = ra ? r.parentNode : r, e = b = 0, d = a.offsetHeight;
        a && (b += a.offsetLeft, e += a.offsetTop);
        b = [b, e, 578, d];
        e = 0 < location.href.indexOf("query=") ? !1 : Za ? !1 : !0;
        a = b[1] + b[3] + (e ? -1 : 0);
        b = b[0] + (e ? 0 : -1);
        qb && ra && (--a, --b);
        t.style.top = a + "px";
        t.style.left = b + "px"
    }

    function I() {
        r.value != J ? (M = !1, Ja()) : setTimeout(I, 10)
    }

    function K() {
        var b = r.value;
        b && J != b && Ka == b ? sa || (sa = setTimeout(function () {
            J = "";
            v = -1;
            k("sug-history", t)[0].style.display = "none";
            $a(b)
        }, 100)) : (clearTimeout(sa), sa = 0, b || M || (c("ppinf") && "undefined" != typeof userSec && userSec && !La && X ? (X = !1, M = !0, na("/sugg/history?queryPlatform=sugg&queryFrom=web&sec=" + userSec + "&op=mine&tags=all::SEARCH_HISTORY::0::10::0::asc&t=" + Math.random(), function (b) {
            (b = JSON.parse(b)) && "ok" == b[0].status && 0 < b[0].subitem.length ? Ea(b[0].subitem, !0) : winLocalStorage && localStorage.getItem("sogouSearchSugPinyin@1.0") && 0 < JSON.parse(localStorage.getItem("sogouSearchSugPinyin@1.0")).length ? Ea(JSON.parse(localStorage.getItem("sogouSearchSugPinyin@1.0"))) : (X = !0, oa())
        }, function () {
            winLocalStorage && localStorage.getItem("sogouSearchSugPinyin@1.0") && 0 < JSON.parse(localStorage.getItem("sogouSearchSugPinyin@1.0")).length ? Ea(JSON.parse(localStorage.getItem("sogouSearchSugPinyin@1.0"))) : (X = !0, oa())
        }, !0)) : oa()), Ka = b);
        b && (M = !1);
        setTimeout(K, 10)
    }

    function na(b, a, e, d) {
        var n = null, n = window.XMLHttpRequest ? new XMLHttpRequest : new ActiveXObject("Microsoft.XMLHTTP");
        n.open("GET", b, d);
        n.send();
        n.onreadystatechange = function () {
            4 == n.readyState && (200 == n.status ? a && a(n.responseText) : e && e())
        }
    }

    function Ea(b, a) {
        ta = !1;
        M = X = !0;
        u = [];
        G = [];
        q = [];
        for (var e = t.getElementsByTagName("ul")[0], d, n; 0 < e.childNodes.length;)e.removeChild(e.childNodes[0]);
        d = b.length;
        n = 10 < d ? d - 10 : 0;
        for (var g = a ? "query_string" : "oq", f = d - 1, h = 0; f >= n; f--, h++) {
            d = A.createElement("li");
            d.style.color = "rgb(122, 119, 200)";
            d.setAttribute("history", "1");
            d.setAttribute("origin_sugg_query", encodeURIComponent(b[f][g]));
            d.onmouseover = ua;
            d.onmouseout = va;
            d.onmousedown = wa;
            d.onclick = xa;
            d.setAttribute("lid", h);
            d.innerHTML = Ma(b[f][g]);
            var l = b[f].arr2 ? b[f].arr2.split(";")[2] : "0";
            if (l && 1 == l || 2 == l)d.innerHTML += "<span></span>", p.tongji.sugVrWordCount++;
            u.push(d);
            G.push(d.innerHTML);
            e.appendChild(d);
            q[h] = b[f][g];
            C[b[f][g]] = {type: l}
        }
        F();
        ya(fa());
        p.cutAllTitle(u, G);
        p.tongji.totalSugCount += u.length;
        p.tongji.totalHistorySugCount += u.length;
        p.tongji.inputEmptyHisWordCount += u.length;
        0 < u.length && (p.tongji.suggHisShowCount++, p.tongji.inputEmptyHisDivCount++, w = "");
        z && (z.style.display = "none", t.className = "suggestion nobg");
        3 < b.length && (k("sug-history", t)[0].style.display = "block", A.getElementById("clear-history").onclick = function () {
            c("ppinf") && "undefined" != typeof userSec && userSec && !La && na("/sugg/history?queryPlatform=sugg&queryFrom=web&sec=" + userSec + "&op=delete&tags=all::SEARCH_HISTORY::::::::&t=" + Math.random(), !1, !1, !0);
            winLocalStorage && localStorage.getItem("sogouSearchSugPinyin@1.0") && localStorage.removeItem("sogouSearchSugPinyin@1.0");
            k("sug-history", t)[0].style.display = "none";
            x("clear_history")
        }, A.getElementById("more-history").onclick = function () {
            x("more_history")
        })
    }

    function oa() {
        if (X)if (clearTimeout(ab), "undefined" == typeof sogou_top_words)ga || (ga = A.createElement("script"), ga.charset = "gb2312", ga.src = "/suggnew/hotwords?v=" + (new Date).getTime(), A.body.appendChild(ga)), ab = setTimeout(oa, 50); else if (!r.value)if (sogou_top_words.length) {
            var b = sogou_top_words, a = [], e, d;
            for (e = b.length - 1; -1 < e; e--) {
                for (d = e - 1; -1 < d;)b[e] === b[d] && (d = --e), d--;
                a.push(b[e])
            }
            sogou_top_words = a.reverse();
            M = ta = !0;
            w = "";
            for (b = t.getElementsByTagName("ul")[0]; 0 < b.childNodes.length;)b.removeChild(b.childNodes[0]);
            u = [];
            G = [];
            q = sogou_top_words;
            e = location.protocol.toLowerCase();
            for (d = 0; d < q.length && 10 > d; d++)a = A.createElement("li"), a.style.height = "27px", a.onmouseover = ua, a.onmouseout = va, a.onmousedown = wa, a.onclick = xa, a.setAttribute("lid", d), a.innerHTML = "<em class='n0' style='background:url(" + e + "//www.sogou.com/sug/images/n_" + (d + 1) + ".gif) no-repeat;'></em>" + q[d] + (0 == d ? ' <img src="/sug/images/new2.gif" align="absmiddle" />' : ""), C[q[d]] = {
                type: 0,
                tupu_key: ""
            }, u.push(a), G.push(a.innerHTML), b.appendChild(a);
            F();
            ya(fa());
            Y.style.display = "";
            z && (z.style.display = "none", t.className = "suggestion nobg");
            p.tongji.totalSugCount += u.length;
            0 < u.length && p.tongji.suggHotShowCount++
        } else N()
    }

    function ob() {
        var b = (new Date).getTime();
        return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (a) {
            var e = (b + 16 * Math.random()) % 16 | 0;
            b = Math.floor(b / 16);
            return ("x" == a ? e : e & 3 | 8).toString(16)
        })
    }

    function Na(b) {
        var a = document.cookie;
        b += "=";
        var e = a.indexOf("; " + b);
        if (-1 == e) {
            if (e = a.indexOf(b), 0 != e)return null
        } else e += 2;
        var d = document.cookie.indexOf(";", e);
        -1 == d && (d = a.length);
        return unescape(a.substring(e + b.length, d))
    }

    function Fa(b) {
        var a = location.search.split("&" + b + "=");
        if (1 < a.length)return a[1].split("&")[0];
        b = location.search.split("?" + b + "=");
        return 1 < b.length ? b[1].split("&")[0] : null
    }

    function bb() {
        var b;
        b = "undefined" != typeof lead_ip && lead_ip ? lead_ip : "";
        b = "&ip=" + b + "&iploc=";
        var a;
        if (a = Na("IPLOC"))for (/CN[0-9]{4,6}/.exec(a) || (a = "CN110000"); 6 > a.length;)a += "0";
        a = a && 2 < a.length ? a.substring(2) : "";
        b = b + a + "&suid=" + Na("SUID") + "&yyid=" + Na("YYID");
        a = Fa("pid");
        var e;
        e = "undefined" != typeof ad_policy_no && ad_policy_no ? ad_policy_no : null;
        a = "&pid=" + (a ? a : "sogou") + "&policyno=" + e + "&mfp=" + Fa("mfp") + "&hs=";
        e = location.protocol;
        e = e.substring(0, e.length - 1);
        return b += a + e + "&mp=" + ("query" == Ia ? 1 : 0)
    }

    function $a(b) {
        T || (T = (new Date).getTime());
        if (D[b] && "function" != typeof D[b])Oa(D[b]); else {
            try {
                A.body.removeChild(ha)
            } catch (a) {
            }
            H = ob();
            ha = A.createElement("script");
            ha.charset = "gb2312";
            ha.src = "/suggnew/ajajjson?key=" + encodeURIComponent(b) + "&type=" + ca + "&ori=yes&pr=" + rb + "&abtestid=" + Q + "&ipn=" + sb + "&t=" + T + "&suguuid=" + H + bb();
            A.body.appendChild(ha)
        }
    }

    function Ma(b) {
        return null != b ? b.replace(/&/g, "&amp;").replace(/ /g, "&nbsp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;") : ""
    }

    function Oa(b) {
        if (!ea && r.value) {
            w = b[0] || w;
            var c, e;
            if (winLocalStorage && window.JSON) {
                e = b[0];
                e = Pa(e);
                if (winLocalStorage)if (e && localStorage.getItem("sogouSearchSugPinyin@1.0")) {
                    for (var d = [], n = JSON.parse(localStorage.getItem("sogouSearchSugPinyin@1.0")), g = n.length - 1; -1 < g; g--) {
                        var f = n[g];
                        0 == f.py.indexOf(e) ? d.push(f) : 0 == f.oq.indexOf(e) && d.push(f);
                        if (1 < d.length)break
                    }
                    e = d
                } else e = !1; else e = void 0;
                try {
                    c = e.length
                } catch (h) {
                }
                b = JSON.parse(JSON.stringify(b));
                n = b[1];
                d = b[2];
                g = b[3];
                if (d && ("0;0;9;0" == d[0] || "0;0;10;0" == d[0]))for (n.splice(0, 0, n[0]), d.splice(0, 0, d[0]), g.splice(0, 0, g[0]), n = "0;0;9;0" == d[0] ? "0" + d[1].substring(5) : "10" + d[1].substring(6), d[1] = d[1].substring(0, 4) + n, n = 0; n < d.length;)d[n] = n + d[n].substring(1), n++;
                d = b;
                if (e && 0 != e.length) {
                    for (var k = d[1], f = d[2], m = d[3], n = f && "0;0;9;0" == f[0] || f && "0;0;10;0" == f[0] ? 1 : 0, g = f && "0;0;8;0" == f[0] ? !0 : !1, y = 0; y < e.length; y++) {
                        for (var v = e[y], z = !1, I = k.slice(0), E = 0; E < I.length; E++)if (I[E] == v.oq) {
                            var K = f[E];
                            if (!K || 4 != K.indexOf("9") && 4 != K.indexOf("10")) {
                                z = !0;
                                k.splice(E, 1);
                                k.splice(n, 0, v.oq);
                                f.splice(E, 1);
                                f.splice(n, 0, K);
                                m.splice(E, 1);
                                m.splice(n, 0, v.arr3);
                                break
                            }
                        }
                        z || (k.splice(n, 0, v.oq), f.splice(n, 0, v.arr2), m.splice(n, 0, v.arr3))
                    }
                    for (; 10 < k.length;)k.pop(), f.pop(), m.pop();
                    e = !1;
                    f && f[0] && (f = f[0].split(";"), f[2] && "8" != f[2] && (e = !0));
                    g && e && x("drop_by_his", d[7]);
                    e = n
                } else e = d
            }
            q = b[1];
            if (2 < b.length)for (O = b[2], "on" == Z("sugdbg", "off") && (O[0] = "0;0;1;0"), da = [], L = [], R = [], za = [], d = 0; d < O.length; ++d)n = O[d].split(";"), C[b[1][d]] = {
                type: n[2],
                tupu_key: b[3][d]
            }, cb[b[1][d]] = {
                arr2: b[2][d],
                arr3: b[3][d]
            }, null != n && 3 <= n.length ? (da.push(n[0]), L.push(n[1]), R.push(n[2])) : (da.push(-1), L.push(0), R.push("0")), za.push(0);
            3 < b.length && (Ga = b[3][0]);
            d = 0;
            U();
            B = -1;
            F();
            n = fa();
            ya("show");
            for (g = t.getElementsByTagName("ul")[0]; 0 < g.childNodes.length;)g.removeChild(g.childNodes[0]);
            u = [];
            G = [];
            k = "&act=show_rows";
            for (d = 0; d < q.length && 10 > d; d++)k += "&row" + d + "=" + q[d], f = A.createElement("li"), m = !1, c && d >= e && d < c + e && (f.style.color = "rgb(122, 119, 200)", f.setAttribute("history", "1"), f.setAttribute("origin_sugg_query", encodeURIComponent(q[d])), m = !0), f.onmouseover = ua, f.onmouseout = va, f.onmousedown = wa, f.onclick = xa, f.setAttribute("lid", d), D[w] && D[w][7] && (H = D[w][7]), 0 == d && b[2] && ("9" == R[0] || "10" == R[0]) && b[5] ? (k += "&has_top_ad=yes", m = "/images/icon-offb.gif", b[5].icon && (m = p.getSuggCdnImgLink(b[5].icon)), f.innerHTML += '<img src="' + m + '"><b>' + b[5].title + "</b><em>" + b[5].show_url + '</em><span id="top_ad_tip" class="tg-txt">\u5e7f\u544a</span>', f.className = "sugad-off", f.id = "top_ad_li", f.setAttribute("data-href", b[5].jump_url), p.pv(b[5].qk, "adtop", 0, 1, null, null, "ad", Q, w, H)) : (y = f, v = Ma(q[d]), z = Ma(w), I = v, 0 == v.indexOf(z) && (I = z + "<b>" + v.substr(z.length) + "</b>"), y.innerHTML = I, m || p.tongji.totalCommonSugCount++), 0 == d && "3" == R[0] && (f.innerHTML = '<strong style="color:red">\u60a8\u662f\u5426\u8981\u627e\uff1a</strong><strong>' + f.innerHTML + "</strong>"), !C[q[d]] || 1 != C[q[d]].type && 2 != C[q[d]].type && 8 != C[q[d]].type && 10 != C[q[d]].type || (f.innerHTML += "<span></span>", p.tongji.sugVrWordCount++), u.push(f), G.push(f.innerHTML), g.appendChild(f), sugTemplate.prototype.cutTitle(f, f.innerHTML);
            N();
            (0 < q.length || a) && Qa && 0 < u.length && (l(u[0], "first"), F(), ya(n), p.tongji.suggDivShowCount++, c && 0 < c && (p.tongji.suggHisShowCount++, p.tongji.totalHistorySugCount += c), p.tongji.totalSugCount += q.length, p.sug_rows_pv(Q, H, k), P = 2, Aa(0));
            p.tongji.suggDivReqCount++;
            ta = !1;
            0 != q.length && Qa || N()
        }
    }

    function Z(b, a) {
        var e = location.href, d = e.indexOf("?");
        if (-1 != d) {
            for (var e = e.substring(d + 1).split("&"), d = e.length, f = 0; f < d; f++) {
                var c = e[f].split("=");
                if (!(2 > c.length)) {
                    var g = c[1];
                    if (c[0] == b)return g
                }
            }
            if (f == d)return a
        } else return a
    }

    function Ba(b) {
        b.preventDefault && b.preventDefault();
        b.cancelBubble = !0;
        return b.returnValue = !1
    }

    function pb(b) {
        b = b || window.event;
        if (ea) {
            if (27 == b.keyCode)return;
            Ja()
        }
        T || (T = (new Date).getTime());
        if (27 == b.keyCode)fa() && (N(), J = r.value, setTimeout(function () {
            J = r.value
        }, 1)); else {
            if (13 == b.keyCode) {
                N();
                Ha = "enter";
                try {
                    y.enter && (y.enter.value = "1")
                } catch (a) {
                }
                y.onsubmit && 0 == y.onsubmit() || db();
                return Ba(b)
            }
            if (fa()) {
                if (38 == b.keyCode)return qa = !0, tb(b), Ba(b);
                if (9 == b.keyCode || 40 == b.keyCode)return qa = !0, ub(b), Ba(b)
            } else if (38 == b.keyCode || 40 == b.keyCode)qa = !0, r.value && $a(r.value)
        }
    }

    function db() {
        setTimeout(function () {
            y.submit()
        }, 100)
    }

    function Pa(b) {
        return b.replace(/^\s+|\s+$/gm, "")
    }

    function ub(b) {
        t.onmousemove || (t.onmousemove = eb);
        Ca = !0;
        clearTimeout(aa);
        Ra(u[B], b) && Sa(u[B]);
        B++;
        B == Math.min(q.length, 10) && (B = -1);
        fb(b)
    }

    function tb(b) {
        t.onmousemove || (t.onmousemove = eb);
        Ca = !0;
        clearTimeout(aa);
        U();
        Ra(u[B], b) && Sa(u[B]);
        B--;
        -2 == B && (B = Math.min(q.length, 10) - 1);
        fb(b)
    }

    function fb(b) {
        U();
        0 <= B ? (l(u[B], "over"), r.value = q[B].replace("<b>", "").replace("</b>", ""), P = 2, Aa(B), gb(u[B], b) && hb(u[B])) : r.value = w;
        v = B;
        J = r.value
    }

    function vb() {
        U();
        0 <= B && l(u[B], "over")
    }

    function U() {
        for (var b = 0; b < u.length; b++) {
            var a = u[b];
            if (a) {
                var e = a.className, e = e.replace(/\bover\b/, "");
                a.className = e
            }
        }
    }

    function eb() {
        t.onmousemove = null;
        Ca = !1;
        clearTimeout(ia);
        ia = setTimeout(function () {
            var b = null;
            0 <= ba && (b = u[ba]) && (U(), l(b, "over"), B = ba, P = 2, Aa(B));
            ba = -1
        }, 50)
    }

    function va(b) {
        b = b || window.event;
        Ra(this, b) && Sa(this);
        clearTimeout(ia)
    }

    function ua(b) {
        b = b || window.event;
        ba = parseInt(this.getAttribute("lid"));
        if (!Ca) {
            U();
            l(this, "over");
            clearTimeout(aa);
            clearTimeout(ia);
            var a = gb(this, b), e = this;
            ia = setTimeout(function () {
                U();
                l(e, "over");
                B = parseInt(e.getAttribute("lid"));
                P = 2;
                Aa(B);
                a && hb(e)
            }, 100)
        }
    }

    function wa(b) {
        b && b.stopPropagation && b.stopPropagation();
        Ta = 1;
        return !1
    }

    function xa() {
        v = parseInt(this.getAttribute("lid"));
        r.value = q[v];
        J = r.value;
        N();
        Ha = "mouse";
        "1" == this.getAttribute("history") && (p.tongji.sugHisClk = 1);
        y.onsubmit && 0 == y.onsubmit() || db()
    }

    function gb(b, a) {
        var e = a.relatedTarget || a.fromElement;
        if (!e || e.parentNode == b || e == b)if (e = b.getElementsByTagName("a"), 9 != a.keyCode && 38 != a.keyCode && 40 != a.keyCode && 1 == e.length)return !1;
        return !0
    }

    function hb(b) {
        if (winLocalStorage && b && b.getAttribute("history")) {
            var a = A.createElement("a");
            a.href = "javascript:void(0);";
            a.onclick = function (a) {
                a = a || window.event;
                Ba(a);
                a = decodeURIComponent(b.getAttribute("origin_sugg_query"));
                var d;
                d = Pa(a);
                if (winLocalStorage && d && localStorage.getItem("sogouSearchSugPinyin@1.0")) {
                    for (var f = JSON.parse(localStorage.getItem("sogouSearchSugPinyin@1.0")), g = 0; g < f.length; g++)if (f[g].oq == d) {
                        f.splice(g, 1);
                        break
                    }
                    localStorage.setItem("sogouSearchSugPinyin@1.0", JSON.stringify(f))
                }
                1 < b.parentNode.childNodes.length ? b.parentNode.removeChild(b) : (d = b.parentNode.parentNode.parentNode, d.parentNode.removeChild(d));
                c("ppinf") && "undefined" != typeof userSec && userSec && !La && na("/sugg/history?queryPlatform=sugg&queryFrom=web&sec=" + userSec + "&op=delete&query=" + encodeURIComponent(a) + "&tags=all::SEARCH_HISTORY::::::::&t=" + Math.random(), !1, !1, !0);
                3 >= k("suglist", t)[0].getElementsByTagName("li").length && (k("sug-history", t)[0].style.display = "none");
                x("del_his_item")
            };
            a.setAttribute("class", "close");
            b.appendChild(a)
        }
    }

    function Ra(b, a) {
        var e = a.relatedTarget || a.toElement;
        return e && e.parentNode != b && e != b || 9 == a.keyCode || 38 == a.keyCode || 40 == a.keyCode ? !0 : !1
    }

    function Sa(b) {
        if (winLocalStorage && b && b.getAttribute("history")) {
            var a = b.getElementsByTagName("a");
            a && a[0] && b.removeChild(a[0])
        }
    }

    function fa() {
        return t && "block" == t.style.display
    }

    function ya(b) {
        b || x("show_s");
        Y.style.display = "none";
        t.style.display = "block";
        try {
            ra ? (r.parentNode.offsetParent.appendChild(t), r.parentNode.offsetParent.appendChild(null)) : (r.offsetParent.appendChild(t), r.offsetParent.appendChild(null))
        } catch (a) {
        }
    }

    function Aa(b) {
        clearTimeout(ja);
        clearTimeout(ka);
        la(!1);
        var a = -1 != navigator.userAgent.indexOf("MSIE 6") && !window.opera;
        if (!C[q[b]] || 0 == C[q[b]].type || 9 == C[q[b]].type || 10 == C[q[b]].type && 0 == b)document.getElementById("top_ad_li") && (a && (u[0].style.width = null), document.getElementById("top_ad_tip").style.display = "", G[0] = document.getElementById("top_ad_li").innerHTML), "none" != z.style.display && (z.style.display = "none", t.className = "suggestion nobg", p.cutAllTitle(u, G)); else if (1 == C[q[b]].type || 2 == C[q[b]].type || 8 == C[q[b]].type || 10 == C[q[b]].type)document.getElementById("top_ad_li") && (a && (u[0].style.width = "202px"), document.getElementById("top_ad_tip").style.display = "none", G[0] = document.getElementById("top_ad_li").innerHTML), Ua(q[b], b)
    }

    function N() {
        t.style.display = "none";
        try {
            ra ? (r.parentNode.offsetParent.removeChild(t), r.parentNode.offsetParent.removeChild(null)) : (r.offsetParent.removeChild(t), r.offsetParent.removeChild(null))
        } catch (b) {
        }
    }

    function Ja() {
        function b(b) {
            return (b = b || window.event) && "submit" == b.type ? (setTimeout(function () {
                0 == y.onsubmit() || y.submit()
            }, 100), !1) : !0
        }

        function a(b, e, f) {
            if ("function" == typeof d && 0 == d(b, e, f))return !1;
            ib();
            b = 0;
            if (null == L || 1 > L.length)pa = 0; else {
                for (e = 0; e < L.length; ++e)f = parseInt(L[e]), b += f;
                pa = b
            }
            b = {w: ma};
            var c;
            D[w] && D[w][7] && (H = D[w][7]);
            -1 != v && (b = {
                w: jb,
                oq: w,
                ri: v,
                sourceid: "sugg",
                suguuid: H
            }, 0 < O.length && ((c = O[v]) && 4 == c.indexOf("8") && (O[v] = c.substring(0, 4) + "0" + c.substring(5)), b = {
                w: jb,
                oq: w,
                ri: v,
                sourceid: "sugg",
                suguuid: H,
                stj: O[v],
                stj2: za[v],
                stj0: da[v],
                stj1: L[v],
                hp: pa,
                hp1: Ga
            }, Va && (b.suglabid = Va)));
            b.sut = T ? (new Date).getTime() - T : 0;
            b.sst0 = (new Date).getTime();
            b.lkt = keypressNum_lead + "," + time1_lead + "," + time2_lead;
            M && (b.p = "01019900" == ma ? "40040108" : "40240100", "index" == wb && ta && (b.pid = "sogou-wsse-af5baf594e9197b4-0001"));
            if (0 < location.href.indexOf("query=") ? 0 : !Za) {
                a:if (e = location.href, f = e.indexOf("?"), -1 != f) {
                    e = e.substring(f + 1).split("&");
                    f = e.length;
                    for (var g = 0; g < f; g++) {
                        var h = e[g].split("=");
                        if (!(2 > h.length)) {
                            var k = h[1];
                            if ("pid" == h[0]) {
                                e = k;
                                break a
                            }
                        }
                    }
                    e = g == f ? "" : void 0
                } else e = "";
                0 < e.length && (b.pid = e)
            }
            e = y.getElementsByTagName("input");
            for (var l in b) {
                for (f = 0; f < e.length; f++)if (e[f].getAttribute("name") == l) {
                    e[f].value = b[l];
                    break
                }
                f == e.length && (f = A.createElement("input"), f.type = "hidden", f.name = l, f.value = b[l], y.appendChild(f))
            }
            if (winLocalStorage && window.JSON && (l = Pa(r.value)) && 40 > l.length) {
                var W;
                a:{
                    try {
                        if (winLocalStorage) {
                            if (!l || !localStorage.getItem("sogouSearchSugPinyin@1.0")) {
                                W = !1;
                                break a
                            }
                            var F = JSON.parse(localStorage.getItem("sogouSearchSugPinyin@1.0"));
                            for (b = 0; b < F.length; b++) {
                                var p = F[b];
                                if (p.oq == l) {
                                    W = p.py;
                                    break a
                                }
                            }
                            W = !1;
                            break a
                        }
                    } catch (m) {
                    }
                    W = void 0
                }
                W ? kb(l, W) : (Da = A.createElement("script"), Da.charset = "gb2312", Da.src = "/suggnew/ajajjson/?type=getpinyin&key=" + encodeURIComponent(l), A.body.appendChild(Da))
            }
            if (c && (4 == c.indexOf("9") || 4 == c.indexOf("10")))return D[w] && D[w][5] && ((c = D[w][5].jump_url) && window.open(c, "_blank").focus(), sugTemplate.prototype.pv(D[w][5].qk, "adtop", 0, 1, "topAd", D[w][5].jump_url, "ad", Q, w, H), v = -1), !1;
            x("sb");
            return !0
        }

        function e() {
            if (xb)for (var b = r.offsetParent; b;)parseInt(b.currentStyle.zIndex) || (b.style.zIndex = "2000"), b = b.offsetParent;
            t = S("div", null, "suggestion nobg");
            t.id = "vl";
            var a, b = S("div", t, "suginner");
            a = S("p", b, "s_title");
            var d = S("ul", b, "suglist"), e = S("div", t, "sug-history");
            Y = a;
            Y.innerHTML = "\u4eca\u65e5\u70ed\u8bcd";
            Y.style.display = "none";
            Y.style.margin = "0";
            u = [];
            for (var f = 0; 10 > f; f++)a = A.createElement("li"), a.onmouseover = ua, a.onmouseout = va, a.onmousedown = wa, a.onclick = xa, a.setAttribute("lid", f), u.push(a), d.appendChild(a);
            z = S("div", b, "sugc");
            z.id = "sugc";
            z.onmouseover = function () {
                ba = -1
            };
            z.style.display = "none";
            d.onmouseout = vb;
            e.style.display = "none";
            e.innerHTML = "on" == window.suggestionPersonalCenterSwitch ? '<a href="javascript:void(0)" id="clear-history">\u6e05\u7a7a\u5386\u53f2</a><span>|</span><a href="/ihome/history" id="more-history" target="_blank">\u67e5\u770b\u66f4\u591a</a>' : '<a href="javascript:void(0)" id="clear-history">\u6e05\u7a7a\u5386\u53f2</a>'
        }

        if (ea) {
            ea = 0;
            J = r.value;
            ma = ma || (y.w && y.w.value ? y.w.value : ma);
            y.onsubmit = b;
            var d = y.onsubmit || function () {
                };
            y.onsubmit = a;
            e();
            h(A, "click", function (b) {
                b = b || window.event;
                for (b = b.srcElement || b.target; b;) {
                    if (b == z || b == r)return;
                    b = b.parentNode
                }
                N()
            });
            h(r, "beforedeactivate", function () {
                Ta && (window.event.cancelBubble = !0, window.event.returnValue = !1, Ta = 0)
            });
            K()
        }
    }

    function ib() {
        var b = parseInt, a;
        a:{
            a = A.cookie;
            var e = a.indexOf("; sct=");
            if (-1 == e) {
                if (e = a.indexOf("sct="), 0 != e) {
                    a = null;
                    break a
                }
            } else e += 2;
            var d = a.indexOf(";", e);
            -1 == d && (d = a.length);
            a = a.substring(e + 4, d)
        }
        b = b(a || 0) || 0;
        document.cookie = "sct=" + (b + 1) + "; expires=Thu, 21-Jul-2020 00:00:00 GMT; path=/;domain=sogou.com;"
    }

    function la(b) {
        for (var a = k("suglist", t)[0], e = a.getElementsByTagName("span"), a = (k("over", a)[0] || a.getElementsByTagName("li")[0]).getElementsByTagName("span")[0], d = 0, f = e.length; d < f; d++)"sugg-loading" === e[d].className && (e[d].className = "");
        if (a)return a.className = b ? "sugg-loading" : "", a
    }

    function Ua(b, a, e) {
        b = (b || "").replace("<b>", "").replace("</b>", "");
        e = e || 0;
        if (b && !Wa[a]) {
            var d = Xa[b];
            clearTimeout(ka);
            clearTimeout(ja);
            ka = setTimeout(function () {
                clearTimeout(ka);
                la(!0)
            }, 100);
            ja = setTimeout(function () {
                clearTimeout(ja);
                var b = la(!1);
                b && b.parentNode.removeChild(b);
                G[a] = G[a].replace("<span></span>", "")
            }, 3E3);
            z && (z.className = "sugc", 0 == e && (z.innerHTML = ""));
            if (d)Ya(Xa[b]); else {
                e = 2 == C[b].type ? 1 : 0;
                b = encodeURIComponent(b);
                try {
                    A.body.removeChild(V)
                } catch (f) {
                }
                V = A.createElement("script");
                if (C && C[q[a]] && ("8" == C[q[a]].type || "10" == C[q[a]].type))b = [{
                    qk: decodeURIComponent(b),
                    pos: a
                }], b = encodeURIComponent(JSON.stringify(b)), e = null, D[w] && D[w][7] && (e = D[w][7]), V.charset = "utf-8", V.src = "/suggnew/adsugg?query=" + b + bb() + "&type=2&cb=window.sogou.site&suguuid=" + e + "&abtest=" + Q; else {
                    d = null;
                    if (0 < document.cookie.length && 0 <= document.cookie.indexOf("IPLOC="))var d = document.cookie.indexOf("IPLOC=") + 6, c = -1 == document.cookie.indexOf(";", d) ? document.cookie.length : document.cookie.indexOf(";", d), d = document.cookie.substring(d, c);
                    for (/CN[0-9]{4,6}/.exec(d) || (d = "CN110000"); 6 > d.length;)d += "0";
                    V.charset = "gb2312";
                    c = "/suggnew/gosugg/";
                    "on" == Z("sugdbg", "off") && Z("zhidaHost", "") && Z("zhidaPort", "") && (c = "http://" + Z("zhidaHost", "") + ":" + Z("zhidaPort", "") + "/");
                    V.src = [c, b, "?", (new Date).getTime(), "&rid=", b.toLowerCase().charCodeAt(b.length - 1) % 6, "&IPLOC=", d, "&type=", e].join("")
                }
                A.body.appendChild(V)
            }
        }
    }

    function kb(b, a) {
        if (!b || !a)return !1;
        var e, d = {};
        if (winLocalStorage) {
            e = localStorage.getItem("sogouSearchSugPinyin@1.0") ? JSON.parse(localStorage.getItem("sogouSearchSugPinyin@1.0")) : [];
            for (var f = 0; f < e.length; f++)e[f].oq == b && e.splice(f, 1);
            d.oq = b;
            d.py = a;
            f = cb[b];
            d.arr2 = f ? f.arr2 : "0;0;0;0";
            d.arr3 = f ? f.arr3 : "";
            f && f.arr2 && (4 == f.arr2.indexOf("9") || 4 == f.arr2.indexOf("8")) && (f = f.arr2, d.arr2 = f.substring(0, 4) + "0" + f.substring(5));
            for (e.push(d); 100 < e.length;)e.shift();
            localStorage.setItem("sogouSearchSugPinyin@1.0", JSON.stringify(e));
            return !0
        }
    }

    function Ya(b, a) {
        var f, d = -1;
        if (z)if (0 == (a || 0) && (z.innerHTML = ""), D[w] && D[w][7] && (H = D[w][7]), f = p.build(z, b, q, za, C, w, H))clearTimeout(ja), clearTimeout(ka), la(!1), t.className = "suggestion", "none" == z.style.display ? (z.style.display = "", p.cutAllTitle(u, G)) : z.style.display = ""; else if (t.className = "suggestion nobg", z.style.display = "none", clearTimeout(lb), clearTimeout(aa), b.query) {
            for (f = 0; f < q.length; f++)if (q[f] == decodeURIComponent(b.query)) {
                d = f;
                0 === a && (Wa[d] = !0, (f = la(!1)) && f.parentNode.removeChild(f), G[d] = G[d].replace("<span></span>", ""));
                break
            }
            aa = setTimeout(function () {
                Ua(decodeURIComponent(b.query), d, 2)
            }, 500)
        }
    }

    "object" != typeof SugPara && (SugPara = {});
    var xb = -1 != navigator.userAgent.indexOf("MSIE") && !window.opera, qb = -1 != navigator.userAgent.indexOf("MSIE 8") && !window.opera, Y, p = new sugTemplate, E = a || SugPara, Qa = a ? 0 : 1, A = document, Ia = E.inputid || "query", ca = E.sugType || "web", Za = E.bigsize || !1, rb = E.productId || ca, jb = E.suggestRid || "", ma = E.normalRid || "", ra = E.useParent || 0, Q = E.abtestid || g(), sb = E.ipn || "", wb = E.sugglocation || "result", ea = 1, t, u = [], G = [], r, y, Ta = 0, J = "", Ka = "", sa = 0, B = -1, q = [], O = [], da = [], L = [], R = [], za = [], pa = 0, Ga = 0, w = "", D = {}, Xa = {}, ha = null, V = null, Da = null, v = -1, T = 0, mb = function () {
    }, nb = function () {
    }, z, lb, aa, ia, S = sugTemplate.prototype.$c, P = 2, C = {}, cb = {}, Ca = !1, ba = -1, ja, ka, H = null, Va, Wa = {}, yb = /\bover\b/, X = !0, ta = !1, La = 0 < navigator.userAgent.toLocaleUpperCase().indexOf("MSIE 7");
    (function () {
        if (winLocalStorage)for (var b = window.localStorage, a = 0; a < b.length; a++) {
            var f = b.key(a);
            if (0 <= f.indexOf("sogouSearchSugPinyin")) {
                var d = f.split("@");
                d[1] && "1.0" == d[1] || localStorage.removeItem(f)
            }
        }
    })();
    this.sw = function (b, a) {
        if (!a)try {
            Oa(["", []])
        } catch (f) {
        }
        Qa = a || !1;
        J = b || "";
        M = !0;
        t && N()
    };
    this.sugTypeChange = function (b) {
        ca = b;
        D = {}
    };
    var ga, ab, M = !1;
    if ("object" != typeof window.sogou || null == window.sogou)window.sogou = {};
    "undefined" != typeof window.sogou.sug && (mb = window.sogou.sug);
    window.sogou.sug = function (b) {
        try {
            mb(b)
        } catch (a) {
        }
        Wa = {};
        b[0] != r.value ? (N(), p.tongji.suggDivReqCount++, x("input_fast_drop", H), H = null) : (1 < b.length && 0 < b[1].length && (b.push(H), H = null, D[b[0]] = b), "string" == typeof b[6] && (Va = b[6]), Oa(b))
    };
    "undefined" != typeof window.sogou.site && (nb = window.sogou.site);
    window.sogou.site = function (b) {
        clearTimeout(lb);
        clearTimeout(void 0);
        clearTimeout(aa);
        try {
            nb(b)
        } catch (a) {
        }
        if (b)if (0 != b.doc_num)P = 2, Xa[decodeURIComponent(b.query)] = b, "ad" == b.vrtype && (b.type = "ad" + b.type), Ya(b); else {
            for (var f = null, e = 0; e < u.length; e++)yb.test(u[e].className) && (f = q[e]);
            0 < P && f == decodeURIComponent(b.query) && (P--, Ya(b, P))
        }
    };
    var qa = !1, Ha = "default", zb = A.onclick || function () {
        };
    A.onclick = function (b) {
        var a = zb(b);
        a:if (!(b && 0 != b.button || !b && 0 != window.event.button))for (b = b || window.event, b = b.target ? b.target : b.srcElement; b && b.tagName;) {
            if ("A" == b.tagName.toUpperCase()) {
                b = b.href || "";
                (0 == b.indexOf("http://www.sogou.com/") || 0 == b.indexOf("https://www.sogou.com/")) && 0 < b.indexOf("query=") && ib();
                break a
            }
            b = b.parentNode
        }
        return a
    };
    window.sogou.sugpy = function (b) {
        var a = b[0], a = a.replace(/^\s+|\s+$/g, "");
        /^\?+\?$/.test(a) || kb(a, b[1][0])
    };
    p.getSiteData = Ua;
    m();
    sugTemplate.prototype.buildZhiDaLoopSection = function (b, a) {
        for (var f = /\x3c!--\s*LOOP\s+SET=\$\{(.*?)\}.*?--\x3e/, d = /\x3c!--\s*ENDLOOP\s*--\x3e/, c = null, g = null; c = f.exec(a);) {
            var h = RegExp.leftContext, k = c[1], g = d.exec(a), l = RegExp.rightContext, c = a.substring(c.index + c[0].length, g.index), g = "";
            with (b)k = eval(k);
            for (var F = 0; F < k.length; F++)b.item = k[F], b.i = F, g += this.replaceZhidaTemp(c, b);
            a = h + g + l
        }
        return a
    };
    sugTemplate.prototype.replaceZhidaTemp = function (a, f) {
        for (var e = /\$\{(.*?)\}|\$%7B(.*?)%7D/g, d = null, c = 0, g = 0, h = ""; d = e.exec(a);) {
            g = d.index;
            h += a.substring(c, g);
            with (f)h += eval(d[1] ? d[1] : d[2]);
            c = g + d[0].length
        }
        return h += a.substring(c, a.length)
    };
    sugTemplate.prototype.buildZhiDaHtml = function (a, f) {
        f = this.buildZhiDaLoopSection(a, f);
        return this.replaceZhidaTemp(f, a)
    };
    sugTemplate.prototype.buildZhiDa = function (a, f, c) {
        a.innerHTML = this.buildZhiDaHtml(f, c)
    }
}
var smugg = new sogouSugg;
function sugg_go_imgresize(a, c, g) {
    var h = a.width || 0, k = a.height || 0;
    if (0 == h || 0 == k) {
        var l = a.cloneNode(!0);
        l.style.visibility = "hidden";
        document.body.appendChild(l);
        h = l.width;
        k = l.height;
        document.body.removeChild(l)
    }
    h > c && k > g && (h / k <= c / g ? (a.style.width = c + "px", a.style.height = "auto") : (a.style.height = g + "px", a.style.width = "auto"))
}
var keypressNum_lead = 0, time1_lead = 0, time2_lead = 0;
(function () {
    function a(a) {
        if (a) {
            if (a.s60ee)return 6;
            if (a.s60se)return 7;
            if (a.aoyou)return 9;
            if (a.world)return 10;
            if (a.worldchrome)return 11;
            if (a.greenbrowser)return 12;
            if (a.qqbrowser)return 13;
            if (a.baidu)return 14;
            if (a.se)return 8;
            if (a.opera)return 4;
            if (a.chrome)return 3;
            if (a.safari)return 5;
            if (a.ie)return 1;
            if (a.firefox)return 2
        }
        return 0
    }

    function c(a) {
        if (a) {
            if (a.mobile)return 6;
            if (a.android)return 7;
            if (a.iphone)return 8;
            if (a.ipad)return 9;
            if (a.window)return 1;
            if (a.linux)return 3;
            if (a.solaris)return 4;
            if (a.unix)return 5;
            if (a.mac)return 2
        }
        return 0
    }

    function g(a, c, g, h, k, l) {
        var m = new Date;
        m.setTime(m.getTime());
        m = new Date(m.getTime() + g);
        document.cookie = a + "=" + c + (g ? "; expires=" + m.toGMTString() : "") + (h ? "; path=" + h : "") + (k ? "; domain=" + k : "") + (l ? "; secure" : "")
    }

    function h(a) {
        var c = document.cookie;
        a += "=";
        var g = c.indexOf("; " + a);
        if (-1 == g) {
            if (g = c.indexOf(a), 0 != g)return null
        } else g += 2;
        var h = document.cookie.indexOf(";", g);
        -1 == h && (h = c.length);
        return unescape(c.substring(g + a.length, h))
    }

    var k = function () {
        var a = window.navigator.userAgent.toLowerCase(), c = {};
        if (window.opera)c.opera = !0; else if (-1 != a.indexOf("msie")) {
            c.ie = !0;
            var g = /msie\s+(.)/.exec(a);
            g && (c["ie" + g[1]] = !0)
        } else-1 != a.indexOf("webkit") ? (c.webkit = !0, -1 != a.indexOf("chrome") ? c.chrome = !0 : -1 != a.indexOf("ipad") ? (c.ipad = !0, c.ios = !0) : -1 != a.indexOf("safari") && (c.safari = !0)) : -1 != a.indexOf("gecko") ? (c.gecko = !0, -1 != a.indexOf("firefox") && (c.firefox = !0)) : window.openDatabase && (c.safari = !0);
        -1 != a.indexOf("se 2.x") && (c.se = !0);
        -1 != a.indexOf("360ee") ? c.s60ee = !0 : -1 != a.indexOf("360se") ? c.s60se = !0 : -1 != a.indexOf("aoyou") || -1 != a.indexOf("maxthon") ? c.aoyou = !0 : -1 != a.indexOf("theworld") ? c.world = !0 : -1 != a.indexOf("worldchrome") ? c.worldchrome = !0 : -1 != a.indexOf("greenbrowser") ? c.greenbrowser = !0 : -1 != a.indexOf("qqbrowser") ? c.qqbrowser = !0 : -1 != a.indexOf("baidu") || -1 != a.indexOf("bidu") ? c.baidu = !0 : -1 != a.indexOf("ucweb") && (c.ucweb = !0);
        return c
    }(), l = function () {
        var a = window.navigator.userAgent.toLowerCase(), c = {};
        -1 != a.indexOf("windows") ? c.window = !0 : -1 != a.indexOf("mac") ? c.mac = !0 : -1 != a.indexOf("linux") ? c.linux = !0 : -1 != a.indexOf("x11") ? c.unix = !0 : -1 != a.indexOf("solaris") && (c.solaris = !0);
        a.match(/AppleWebKit.*Mobile.*/) || a.match(/AppleWebKit/) ? c.mobile = !0 : -1 != a.indexOf("ios") ? c.mobile = !0 : -1 != a.indexOf("ipad") ? c.ipad = !0 : -1 != a.indexOf("android") ? c.android = !0 : -1 != a.indexOf("iphone") && (c.iphone = !0);
        return c
    }();
    (function () {
        h("browerV");
        h("osV");
        var f;
        f = a(k);
        g("browerV", f, 94608E4, "/", "www.sogou.com", "");
        f = c(l);
        g("osV", f, 94608E4, "/", "www.sogou.com", "")
    })();
    var x = document.getElementsByTagName("input"), m;
    for (m in x)for (i = 0; i < x.length; i++)"query" == x[i].name && (x[i].onkeypress = function () {
        keypressNum_lead++;
        0 == time1_lead && (time1_lead = (new Date).getTime());
        time2_lead = (new Date).getTime()
    })
})();