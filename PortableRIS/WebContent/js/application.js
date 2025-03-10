$(document).ready(function(){
  // ログイン画面ViewModel
  var loginVM = function(){
    var self = this;
    self.userId = ko.observable("");
    self.password = ko.observable("");

    // 2021.09.01 Add Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無対応
    async.series({
    	loginsystem: function(callback){
        comm.get("loginsystem", null, callback);
      },
    }, function(err, results){
      try{
        if(err){
          if(err.errlevel && (err.errlevel == "WARN")){
            vm.showMessageDialog(err.message, null, function(){
            });
          }else{
            throw new Error(err);
          }
        }else{
        	vm.loginEscKeyFlg(results.loginsystem.loginesckey_flg);
        }
      }finally{
      }
    });
    // 2021.09.01 Add Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無対応


    self.clearUserId = function(){
      self.userId("");
      $("#inputUserId").focus();
    };

    window.onkeydown = function(e){
      if(e.keyCode == 27){ // ESCキー：PC用コード。
    	// 2021.09.01 mod Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無対応
    	if(vm.loginEscKeyFlg()){
          $("#loginClear").focus();
          $("#loginClear").click();
        }
    	// 2021.09.01 mod Nishihara@COSMO end ログイン画面のESCキーフォーカス除外有無対応
      }else if(e.keyCode == 0){ // ESCキー等：iOS用コード。
      	// 2021.09.01 mod Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無対応
    	  if(vm.loginEscKeyFlg()){
    	  if($("#inputUserId")[0]){
    		self.userId("");
    		$("#inputUserId").focus();
    	  }
      	}
      	// 2021.09.01 mod Nishihara@COSMO end ログイン画面のESCキーフォーカス除外有無対応
      }else if(e.keyCode == 13){ // Enterキー
        if($(document.activeElement).attr("id") == "inputPassword"){
          $("#btnLogin").focus();
          $("#btnLogin").click();
        }
      }
    }
  };

  // 一覧画面ViewModel
  var orderListVM = function(){
    var self = this;
    self.kensaDate = ko.observable();
    self.kanjaId = ko.observable();
    self.selectedByoutou = ko.observable();
    self.selectedKensaType = ko.observable();
    self.selectedUketuke = ko.observable();
    self.orders = ko.observableArray([]);
    self.lastSearchParams = null;
    self.selectedNyugai = ko.observable();
    self.selectedPortable = ko.observable();
    self.selectedSort = ko.observable();

    self.initialize = function(clearValues){
      $('#calendar-text').datepicker({
        format: "yyyy年 mm月 dd日 (D)",
        todayBtn: "linked",
        language: "ja",
        orientation: "top auto",
        autoclose: true,
        todayHighlight: true
      });

      if(clearValues){
        $('#calendar-text').datepicker('update', new Date(vm.systemDate()));
        self.kanjaId("");
        self.selectedByoutou(undefined);
        // 2020.09.10 mod Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
        //検査種別プルダウンの初期値を設定
//         self.selectedKensaType(undefined);
        self.selectedKensaType(vm.beforselectedKensatype());
        // 2020.09.10 mod Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)
        self.selectedUketuke(undefined);
        self.orders([]);
        self.lastSearchParams = null;
        self.selectedUketuke(undefined);
        // 2020.09.10 mod Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
        //入外区分プルダウンの初期値を設定
//         self.selectedNyugai(undefined);
        self.selectedNyugai(vm.beforselectedNyugai());
        // 2020.09.10 mod Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)
        self.selectedPortable(undefined);
        self.selectedSort(undefined);
        // 2020.08.28 mod Nishihara@COSMO start MWM対象装置の制御追加(検査室・検査機器)
        //検査室、検査機器プルダウンの初期値を設定
        // vm.selectedKensasitu(undefined);
        // vm.selectedKensaKiki(undefined);
        vm.initialKensasitu();
        vm.selectedKensasitu(vm.beforselectedKensasitu());
        vm.selectedKensaKiki(vm.beforselectedKensakiki());
        // 2020.08.28 Add Nishihara@COSMO end MWM対象装置の制御追加(検査室・検査機器)
      }
    };

    self.search = function(status){
      var params = {
        kensa_date: parseInt(util.dateToYYYYMMDD($('#calendar-text').datepicker('getDate')), 10),
        kanja_id: self.kanjaId(),
        uketuke_tantou_id: self.selectedUketuke(),
        byoutou_id: self.selectedByoutou(),
        kensatype_id: self.selectedKensaType(),
        nyugaikbn: self.selectedNyugai(),
        portablekbn: self.selectedPortable(),
        status: status,
        sort: self.selectedSort(),
      };
      self.doSearch(params);

      // 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応
    //検査進捗ボタンフラグがtrueだったら、設定ファイルで設定したONの時の色で表示する。(各検索時)
      if(vm.statusColorFlg()){
    	  vm.getStatusColor_conf(status);
      }
      // 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応
    };

    self.refresh = function(){
      //self.clearKanjaId();
      self.lastSearchParams.kanja_id = "";
      self.doSearch(self.lastSearchParams);
    }

    self.doSearch = function(params){
      //self.orders([]);  // セッションタイムアウト時は初期化しないようにcallbackへ移動
      vm.showProgressDialog();
      async.series({
        orderlist: function(callback){
          comm.get("orderlist", params, callback);
        },
      }, function(err, results){
        try{
          if(!(err && err.errlevel && err.errlevel == "NO_SESSION")){
            // セッションタイムアウト以外は一覧を初期化
            self.orders([]);
          }
          if(err){
            if(err.errlevel && (err.errlevel == "WARN")){
              vm.showMessageDialog(err.message);
            }else if(err.errlevel && (err.errlevel == "NO_SESSION")){
              // セッションタイムアウト時は再ログインダイアログを表示
              vm.showReloginDialog();
            }else{
              throw new Error(err);
            }
          }else{
            self.orders(results.orderlist.order_array);
            self.lastSearchParams = params;
          }
        }finally{
          vm.hideProgressDialog();
        }
      });
    };

    self.changeStatus = function(url, ris_id){
      vm.showProgressDialog();
      async.series({
        changeStatus: function(callback){
          var params = {ris_id: ris_id};
          comm.post(url, params, callback);
        },
      }, function(err, results){
        try{
          if(err){
            if(err.errlevel && (err.errlevel == "WARN")){
              vm.showMessageDialog(err.message, null, function(){
                self.refresh();
              });
            }else if(err.errlevel && (err.errlevel == "NO_SESSION")){
              // セッションタイムアウト時は再ログインダイアログを表示
              vm.showReloginDialog();
            }else{
              throw new Error(err);
            }
          }else{
            self.refresh();
          }
        }finally{
          vm.hideProgressDialog();
        }
      });
    };

    self.register = function(order){
        //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
    	//一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で渡す処理に変更
    	vm.showProgressDialog();
   		var ris_id_array = [];
  		ris_id_array.push(order.ris_id);
        async.series({
            changeStatus: function(callback){
//              var params = {ris_id: order.ris_id};
                var params = {ris_id: ris_id_array};
         //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
              comm.post("status/registered2", params, callback);
            },
          }, function(err, results){
            try{
              if(err){
                if(err.errlevel && (err.errlevel == "WARN")){
                  vm.showMessageDialog(err.message, null, function(){
                    self.refresh();
                  });
                }else if(err.errlevel && (err.errlevel == "NO_SESSION")){
                  // セッションタイムアウト時は再ログインダイアログを表示
                  vm.showReloginDialog();
                }else{
                  throw new Error(err);
                }
              }else{
                self.refresh();
                var checkEl = document.getElementById("mwm_" + order.ris_id);
              // MWM登録チェックボックスの実体があり、且つチェック入りなら詳細、開始押下処理
                if(checkEl && checkEl.checked){
            	  vm.openDetail(order, "true");
              	}
              	// 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応
              	// TransitionFlgがtrueの場合、受付ボタン押下後詳細画面へ遷移  2020.03.06 Add Nishihara@COSMO
              	else if(order.transition_flg == true){
            	  vm.openDetail(order);
              	}
              }
            }finally{
              vm.hideProgressDialog();
            }
          });
    };

    //2020.09.08 Add Nishihara@COSMO start 複数オーダーの一括処理機能追加 一括受付対応
    //●受付ボタン処理(一括受済処理)
    self.ukezumistart = function(order){
       //2021.01.12 Add Nishihara@COSMO start
       if(order.length != 0){
       //2021.01.12 Add Nishihara@COSMO end
     	 vm.showProgressDialog();
  		  var ris_id_array = [];
		  for(var i=0; i<order.length; i++){
			  var checkEl = document.getElementById("mwm_" + order[i].ris_id);
			  if(checkEl && checkEl.checked && order[i].status == '0'){
				  ris_id_array.push(order[i].ris_id);
			  }
		   }
		  async.series({
			  changeStatus: function(callback){
				  var params = {ris_id: ris_id_array,
						  		kensakiki_id: vm.selectedKensaKiki()};
				  comm.post("status/lumpregistered", params, callback);
			  },
		  }, function(err, results){
			  try{
				  if(err){
					  if(err.errlevel && (err.errlevel == "WARN")){
						  vm.showMessageDialog(err.message, null, function(){
							  self.refresh();
						  });
					  }else if(err.errlevel && (err.errlevel == "NO_SESSION")){
						  // セッションタイムアウト時は再ログインダイアログを表示
						  vm.showReloginDialog();
					  }else{
						  throw new Error(err);
					  }
				  }else{
					  self.refresh();
				  }
			  }finally{
				  vm.hideProgressDialog();
			  }
		  });
       }
    };

      //2020.09.08 Add Nishihara@COSMO end 複数オーダーの一括処理機能追加 一括受付対応

    //2020.09.07 Add Nishihara@COSMO start 複数オーダーの一括処理機能追加 一括MWM登録対応
    //●MWMボタン処理(一括MWM登録処理)

    self.mwmstart = function(order){
      //2021.01.12 Add Nishihara@COSMO start
      if(order.length != 0){
      //2021.01.12 Add Nishihara@COSMO end
      	 vm.showProgressDialog();
   		  var ris_id_array = [];
   		  //画面上のオーダで条件に当てはまるオーダのris_idを配列に格納
		  for(var i=0; i<order.length; i++){
			  var checkEl = document.getElementById("mwm_" + order[i].ris_id);
			  if(checkEl && checkEl.checked && order[i].status == '10'){
				  ris_id_array.push(order[i].ris_id);
			  }
		   }
		  async.series({
			  changeStatus: function(callback){
				  var params = {ris_id: ris_id_array,
						  kensasitu_id: vm.selectedKensasitu(),
						  kensakiki_id: vm.selectedKensaKiki()};
				  comm.post("status/mwmregistered", params, callback);
			  },
		  }, function(err, results){
			  try{
				  if(err){
					  if(err.errlevel && (err.errlevel == "WARN")){
						  vm.showMessageDialog(err.message, null, function(){
							  self.refresh();
						  });
					  }else if(err.errlevel && (err.errlevel == "NO_SESSION")){
						  // セッションタイムアウト時は再ログインダイアログを表示
						  vm.showReloginDialog();
					  }else{
						  throw new Error(err);
					  }
				  }else{
					  self.refresh();
				  }
			  }finally{
				  vm.hideProgressDialog();
			  }
		  });
       }
     };

    //2020.09.07 Add Nishihara@COSMO end 複数オーダーの一括処理機能追加 一括MWM登録対応

    self.rest = function(order){
        //2020.09.07 Add Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
    	//一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で渡す処理に変更
  		var ris_id_array = [];
  		ris_id_array.push(order.ris_id);
  		//2020.09.07 Add Nishihara@COSMO end 複数オーダーの一括処理機能追加複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
        self.changeStatus(
          "status/rest",
          {
        	//2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
//          ris_id: order.ris_id(),
            ris_id: ris_id_array,
            //2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
            kensasitu_id: vm.selectedKensasitu(),
            kensakiki_id: vm.selectedKensaKiki()
          },
          function(result){
            vm.closeDetail(true);
          }
        );
      };

//    self.register = function(order){
//        // 2019.08.06 Mod COSMO End 排他対応
//    	//self.changeStatus("status/registered", order.ris_id);
//        self.changeStatus("status/registered2", order.ris_id);
//        // 2019.08.06 Mod COSMO End 排他対応
//      // 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
//      var checkEl = document.getElementById("mwm_" + order.ris_id);
//
//      // MWM登録チェックボックスの実体があり、且つチェック入りなら詳細、開始押下処理
//      if(checkEl && checkEl.checked){
//        vm.openDetail(order, "true");
//      }
//      // 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応
//    };

    self.unregister = function(order){
        // 2019.08.06 Mod COSMO End 排他対応
        //self.changeStatus("status/unregistered", order.ris_id);
        // 2019.08.06 Mod COSMO End 排他対応

        //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
    	//一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で渡す処理に変更
    	// self.changeStatus("status/unregistered2", order.ris_id);
  		var ris_id_array = [];
  		ris_id_array.push(order.ris_id);
        self.changeStatus("status/unregistered2", ris_id_array);
        //2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)

    };

    self.padKanjaId = function(){
      var s = self.kanjaId();
      if(!s){
        return;
      }
      self.kanjaId(util.zeroPadding(s, vm.kanjaIdLength()));
    };

    self.clearKanjaId = function(){
      self.kanjaId("");
      $("#kanjaid").focus();
    };

    window.onkeydown = function(e){
      //alert(e.keyCode);
      if(e.keyCode == 27){ // ESCキー：PC用コード。
          $("#clear").focus();
          $("#clear").click();
      }else if(e.keyCode == 0){ // ESCキー等：iOS用コード。
        if($("#kanjaid")[0]){
          self.kanjaId("");
          $("#kanjaid").focus();
        }
      }else if(e.keyCode == 13){ // Enterキー
        if($(document.activeElement).attr("id") == "kanjaid"){
          $("#search").focus();
          $("#search").click();
        }
      }
    }

    //2020.09.02 Add Nishihara@COSMO start MWMチェックボックス一括選択、一括解除追加対応
    //オーダーのチェックボックス一括選択/解除(引数にtrueが来た場合:一括選択、引数にfalseが来た場合:一括解除)
    self.allcheck = function (tf) {
      var checkboxes = document.getElementsByName('ordercheck');
      var checkboxCount = checkboxes.length; // チェックボックスの数
      for(var i=0; i<checkboxCount; i++) {
    	  checkboxes[i].checked = tf;
      }
    }
    //2020.09.02 Add Nishihara@COSMO end MWMチェックボックス一括選択、一括解除追加対応
  };

  // 詳細画面ViewModel
  // 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
  var orderDetailVM = function(orderDetail, isMWM){
  //var orderDetailVM = function(orderDetail){
  // 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応
    var self = this;
    self.orderDetail = ko.observable(ko.mapping.fromJS(orderDetail));
    self.selectedTemplate = ko.observable();
    self.startEnabled = ko.observable(false);

    //2020.09.14 Add Nishihara@COSMO start 実施技師登録機能追加対応
    //右端のプルダウンにはMED_PERSON_IDがnullの場合、初期値にログイン者を設定
    if(self.orderDetail().med_person_id01()){
        self.selectedZisshishaId1 = ko.observable(self.orderDetail().med_person_id01());
    }else{
        self.selectedZisshishaId1 = ko.observable(vm.loginUserId());
    }

    self.selectedZisshishaId2 = ko.observable(self.orderDetail().med_person_id02());
    self.selectedZisshishaId3 = ko.observable(self.orderDetail().med_person_id03());

    //2020.09.14 Add Nishihara@COSMO end 実施技師登録機能追加対応

    // mod sta 201806_ポータブルRIS検査系種別対応
    self.kensaKbn = self.orderDetail().kensa_kbn();
    if(self.kensaKbn == "1"){
        self.mppsChecked = ko.observable(true);
    } else {
        self.mppsChecked = ko.observable(false);
    }
    // mod end 201806_ポータブルRIS検査系種別対応

    // MPPSが無効の場合は、チェックをOFFにする
    if(!vm.useMPPSFlg()){
        self.mppsChecked = ko.observable(false);
    }

    self.addTemplate = function(){
      if(self.selectedTemplate()){
        self.orderDetail().renraku_memo((self.orderDetail().renraku_memo() + util.idToName(vm.templateMaster(), self.selectedTemplate())).substr(0, 250));
      }
    };

    self.changeStatus = function(url, params, onSuccess, onError){
      vm.showProgressDialog();
      async.series({
        changeStatus: function(callback){
          comm.post(url, params, callback);
        },
      }, function(err, results){
        try{
          if(err){
            if(onError){
              onError(err);
            }else{
              if(err.errlevel && (err.errlevel == "WARN")){
                vm.showMessageDialog(err.message);
              }else if(err.errlevel && (err.errlevel == "NO_SESSION")){
                // セッションタイムアウト時は再ログインダイアログを表示
                vm.showReloginDialog();
              }else{
                throw new Error(err);
              }
            }
          }else{
            if(onSuccess){
              onSuccess(results.changeStatus);
            }else{
              // 2019.03.07 Mod H.Watanabe@COSMO Start 20190301：山形中央病院向け対応
              //self.refresh();
              self.refresh(url);
              // 2019.03.07 Mod H.Watanabe@COSMO Start 20190301：山形中央病院向け対応
            }
          }
        }finally{
          vm.hideProgressDialog();
        }
      });
    };

    // 2019.03.07 Add H.Watanabe@COSMO Start 20190301：山形中央病院向け対応
    self.refresh = function(url){
    //self.refresh = function(){
    // 2019.03.07 Add H.Watanabe@COSMO Start 20190301：山形中央病院向け対応
      var params = {
        ris_id: self.orderDetail().ris_id()
      };
      vm.showProgressDialog();
      async.series({
        orderdetail: function(callback){
          comm.get("orderdetail", params, callback);
        },
      }, function(err, results){
        try{
          if(err){
            if(err.errlevel && (err.errlevel == "WARN")){
              vm.showMessageDialog(err.message);
            }else{
              throw new Error(err);
            }
          }else{
            ko.mapping.fromJS(results.orderdetail.order_detail, self.orderDetail());
          }
        }finally{
          // 2019.03.07 Add H.Watanabe@COSMO Start 20190301：山形中央病院向け対応
          if(url == 'status/inoperation'){
             self.startEnabled(true);
          }
          // 2019.03.07 Add H.Watanacbe@COSMO End   20190301：山形中央病院向け対応
          vm.hideProgressDialog();
        }
      });
    };

    self.back = function(order){
        //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
    	//一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で渡す処理に変更
  		var ris_id_array = [];
  		ris_id_array.push(order.ris_id);
    	if(order.status() == '20'){
            vm.showMessageDialog("変更内容を破棄しますか？", ["はい", "いいえ"], function(result){
                if(result == 0){
                    self.changeStatus(
                            "status/back",
                            {
//                              ris_id: order.ris_id()
                            	ris_id: ris_id_array
                            },
                            function(result){
                               //2020.09.03 mod Nishihara@COSMO start 町田市民病院PortableRIS改修対応
                               //再検索するように変更。
                               // vm.closeDetail(false);
                               vm.closeDetail(true);
                               //2020.09.03 mod Nishihara@COSMO start 町田市民病院PortableRIS改修対応
                            }
                     );
                }
              });
    	}else{
            self.changeStatus(
                    "status/back",
                    {
//                      ris_id: order.ris_id()
                    	ris_id: ris_id_array
                    },
                    function(result){
            			//2020.12.09 Mod Nishihara@COSMO start
//                      vm.closeDetail(false);
                        vm.closeDetail(true);
                       //2020.12.09 Mod Nishihara@COSMO end
                    }
                  );
    	}
    	//2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
    };
    self.register = function(order){
      //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
      //一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で渡す処理に変更
  	  var ris_id_array = [];
  	  ris_id_array.push(order.ris_id);
      self.changeStatus(
        "status/registered",
        {
//          ris_id: order.ris_id()
        	ris_id: ris_id_array

        }
      //2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
      );
    };

    self.unregister = function(order){
      //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
      //一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で渡す処理に変更
  	  var ris_id_array = [];
  	  ris_id_array.push(order.ris_id);
      self.changeStatus(
        "status/unregistered",
        {
//          ris_id: order.ris_id()
        	ris_id: ris_id_array
        }
      );
      //2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
    };

    self.start = function(order){
        //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
    	//一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で渡す処理に変更
  		var ris_id_array = [];
  		ris_id_array.push(order.ris_id);

//  		var zisshishaId = {zisshisha1:self.selectedZisshishaId1(), zisshisha2:self.selectedZisshishaId2(), zisshisha3:self.selectedZisshishaId3()};
//  		var zisshishaName = {zisshisha1:$('#zisshisha1 option:selected').text(), zisshisha2:$('#zisshisha2 option:selected').text(), zisshisha3:$('#zisshisha3 option:selected').text()};

  		var ZisshishaName1 = $('#zisshisha1 option:selected').text();
  		var ZisshishaName2 = $('#zisshisha2 option:selected').text();
  	    var ZisshishaName3 = $('#zisshisha3 option:selected').text();

  	      self.changeStatus(
	        "status/inoperation",
	        {
	          // 2019.03.07 Mod H.Watanabe@COSMO Start 20190301：山形中央病院向け対応
	//        ris_id: order.ris_id(),
	          ris_id: ris_id_array,
	          // 2019.03.07 Mod H.Watanabe@COSMO End   20190301：山形中央病院向け対応
	          //2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
	          kensasitu_id: vm.selectedKensasitu(),
	          kensakiki_id: vm.selectedKensaKiki(),
	          //2020.09.10 Add Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
	          kanja_nyugai: vm.orderListObj.selectedNyugai(),
	          kensatype_id: vm.orderListObj.selectedKensaType(),
	          //2020.09.10 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)
	          //2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
	          zisshisha_id1: self.selectedZisshishaId1(),
	          zisshisha_id2: self.selectedZisshishaId2(),
	          zisshisha_id3: self.selectedZisshishaId3(),
	          zisshisha_name1: ZisshishaName1,
	          zisshisha_name2: ZisshishaName2,
	          zisshisha_name3: ZisshishaName3
	          //2020.09.16 Add Nishihara@COSMO end 実施技師登録機能追加対応
	          // 2021.12.15 Add H.Taira@COSMO Start
	          ,status:order.status()
	          // 2021.12.15 Add H.Taira@COSMO End
	        }
	      );
      // 2019.03.07 Del H.Watanabe@COSMO Start 20190301：山形中央病院向け対応
      //self.startEnabled(true);
      // 2019.03.07 Del H.Watanabe@COSMO End   20190301：山形中央病院向け対応
    };
    // 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
    if(isMWM == "true"){
      self.start(orderDetail);
    }
    // 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応

    self.rest = function(order){
        //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
    	//一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で渡す処理に変更
  		var ris_id_array = [];
  		ris_id_array.push(order.ris_id);
  		var ZisshishaName1 = $('#zisshisha1 option:selected').text();
  		var ZisshishaName2 = $('#zisshisha2 option:selected').text();
  	    var ZisshishaName3 = $('#zisshisha3 option:selected').text();

	      self.changeStatus(
	        "status/rest",
	        {
	          // ris_id: order.ris_id(),
	          ris_id: ris_id_array,
	          //2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
	          kensasitu_id: vm.selectedKensasitu(),
	          kensakiki_id: vm.selectedKensaKiki(),
	          //2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
	          zisshisha_id1: self.selectedZisshishaId1(),
	          zisshisha_id2: self.selectedZisshishaId2(),
	          zisshisha_id3: self.selectedZisshishaId3(),
	          zisshisha_name1: ZisshishaName1,
	          zisshisha_name2: ZisshishaName2,
	          zisshisha_name3: ZisshishaName3
	          //2020.09.16 Add Nishihara@COSMO end 実施技師登録機能追加対応
	        },
	        function(result){
	          vm.closeDetail(true);
	        }
	      );
    };

    self.finish = function(order){
        //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
    	//一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で渡す処理に変更
  		var ris_id_array = [];
  		ris_id_array.push(order.ris_id);
  		var ZisshishaName1 = $('#zisshisha1 option:selected').text();
  		var ZisshishaName2 = $('#zisshisha2 option:selected').text();
  	    var ZisshishaName3 = $('#zisshisha3 option:selected').text();

	      self.changeStatus(
	        "status/finished",
	        {
	          // ris_id: order.ris_id(),
	          ris_id: ris_id_array,
	          //2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加(一括受付、一括MWM登録機能追加に伴い変更)
	          kensasitu_id: vm.selectedKensasitu(),
	          kensakiki_id: vm.selectedKensaKiki(),
	          //2020.09.10 Add Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
	          kanja_nyugai: vm.orderListObj.selectedNyugai(),
	          kensatype_id: vm.orderListObj.selectedKensaType(),
	          //2020.09.10 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)
	          //2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
	          zisshisha_id1: self.selectedZisshishaId1(),
	          zisshisha_id2: self.selectedZisshishaId2(),
	          zisshisha_id3: self.selectedZisshishaId3(),
	          zisshisha_name1: ZisshishaName1,
	          zisshisha_name2: ZisshishaName2,
	          zisshisha_name3: ZisshishaName3,
	          //2020.09.16 Add Nishihara@COSMO end 実施技師登録機能追加対応
	          mpps: self.mppsChecked() ? 1 : 0
	        },
	        function(result){
	          if(self.mppsChecked()){
	            if(result.satuei_array && result.mpps_array){
	              self.showDeferDialog(result.satuei_array, result.mpps_array, function(){vm.closeDetail(true)});
	            }else{
	              vm.closeDetail(true);
	            }
	          }else{
	            vm.closeDetail(true);
	          }
	        }
	      );
    };

    self.updateMemo = function(order){
      vm.showProgressDialog();
      async.series({
        updateMemo: function(callback){
          var params = {
            ris_id: order.ris_id,
            renraku_memo: order.renraku_memo,
          };
          comm.post("memo", params, callback);
        },
      }, function(err, results){
        try{
          if(err){
            if(err.errlevel && (err.errlevel == "WARN")){
              vm.showMessageDialog(err.message);
            }else if(err.errlevel && (err.errlevel == "NO_SESSION")){
              // セッションタイムアウト時は再ログインダイアログを表示
              vm.showReloginDialog();
            }else{
              throw new Error(err);
            }
          }else{
          }
        }finally{
          vm.hideProgressDialog();
        }
      });
    };

    //// 保留ダイアログ
    // 保留ダイアログViewModel
    var deferModalDlgVM = function(risSatueiInfo, mppsInfo){
      var dlg = this;
      dlg.risSatueiInfo = ko.observableArray(risSatueiInfo);
      dlg.mppsInfo = ko.observableArray(mppsInfo);
    };

    self.deferModalDlg = ko.observable();

    // 保留ダイアログ表示メソッド
    self.showDeferDialog = function(risSatueiInfo, mppsInfo, callback){
      self.deferModalDlg(new deferModalDlgVM(risSatueiInfo, mppsInfo));
      $("#deferModal").on("hidden.bs.modal", function () {
        self.deferModalDlg(null);
        if(callback){
          callback();
        }
      });
      $("#deferModal").modal();
    };
  };

  vm = new function(){
    var self = this;

    //// マスタ
    self.uketukeMaster = ko.observableArray();
    self.byoutouMaster = ko.observableArray();
    self.kensaTypeMaster = ko.observableArray();
    self.kensasituMaster = ko.observableArray();
    self.selectedKensasitu = ko.observable();
    self.kensaKikiMaster = ko.observableArray();
    self.statusMaster = ko.observableArray();
    self.templateMaster = ko.observableArray();
    self.nyugaiMaster = ko.observableArray();
    self.portableMaster = ko.observableArray();
    self.sortMaster = ko.observableArray(
      [
        {"id": "1", "name": "病棟順"},
        {"id": "0", "name": "名前順"}
      ]
    );

    // 2020.09.14 Add Nishihara@COSMO start 実施技師登録機能追加対応
    self.zisshishaMaster = ko.observableArray();
    // 2020.09.14 Add Nishihara@COSMO end 実施技師登録機能追加対応

    //// システム変数
    self.systemDate = ko.observable();
    self.kanjaIdLength = ko.observable();
    self.renrakuMemoLength = ko.observable();
    self.useMPPSFlg = ko.observable();
    self.usePortableKbnFlg = ko.observable();
    // 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
    self.useReceiptMWMFlg = ko.observable();
    // 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応
    // 2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
    self.statusColorFlg = ko.observable();
    // 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

    //// 画面間共通
    self.loginUserId = ko.observable();
    self.loginUserName = ko.observable();
    // 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
    self.isReadMode = ko.observable();
    // 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
    self.selectedKensaKiki = ko.observable();

    // 2020.08.26 Add Nishihara@COSMO start MWM対象装置の制御追加(検査室・検査機器)
    self.beforselectedKensasitu = ko.observable();
    self.beforselectedKensakiki = ko.observable();
    // 2020.08.26 Add Nishihara@COSMO end MWM対象装置の制御追加(検査室・検査機器)

    // 2020.09.09 Add Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
    self.beforselectedNyugai = ko.observable();
    self.beforselectedKensatype = ko.observable();
    // 2020.09.09 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)

    // 2021.09.01 Add Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無対応
    self.loginEscKeyFlg = ko.observable();
    // 2021.09.01 Add Nishihara@COSMO end ログイン画面のESCキーフォーカス除外有無対応

    //// 画面
    self.loginForm = ko.observable(new loginVM());
    self.orderListForm = ko.observable();
    self.orderDetailForm = ko.observable();
    self.reloginModalDlg = ko.observable();
    self.messageModalDlg = ko.observable();
    self.errorModalDlg = ko.observable();
    self.progressModalDlg = ko.observable();

    self.orderListObj = null;
  };

  // 2018.12.20 Mod H.Watanabe@COSMO Start 呼出参照機能対応
  // vm.doLogin = function() {
  vm.doLogin = function(mode) {
  // 2018.12.20 Mod H.Watanabe@COSMO End   呼出参照機能対応
    vm.showProgressDialog();
    async.series({
      login: function(callback){
        var params = {
          userid: vm.loginForm().userId(),
          password: vm.loginForm().password(),
          // 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
          mode: mode
          // 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
        };
        comm.post("login", params, callback);
      },

      system: function(callback){
        comm.get("system", null, callback);
      },

      master: function(callback){
        comm.get("master", null, callback);
      },
    }, function(err, results){
      try{
        if(err){
          if(err.errlevel && (err.errlevel == "WARN")){
            vm.showMessageDialog(err.message, null, function(){
              vm.loginForm().password("");
            });
          }else{
            throw new Error(err);
          }
        }else{
          vm.loginUserId(results.login.userid);
          vm.loginUserName(results.login.username);
          // 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
          vm.isReadMode(results.login.isReadMode);
          // 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応

          // 2020.08.27 Add Nishihara@COSMO start MWM対象装置の制御追加(検査室・検査機器)
          vm.beforselectedKensasitu(results.login.kensasituid_pulldown);
          vm.beforselectedKensakiki(results.login.kensakikiid_pulldown);
          // 2020.08.27 Add Nishihara@COSMO end MWM対象装置の制御追加(検査室・検査機器)

          // 2020.09.09 Add Nishihara@COSMO start検索一覧初期表示対応(入外区分・検査種別)
          vm.beforselectedNyugai(results.login.kanjanyugai_pulldown);
          vm.beforselectedKensatype(results.login.kensatypeid_pulldown);
          // 2020.09.09 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)

          vm.systemDate(results.system.sysdate);
          vm.kanjaIdLength(results.system.kanjaidlen);
          vm.renrakuMemoLength(results.system.renrakumemolen);
          vm.useMPPSFlg(results.system.use_mpps);
          vm.usePortableKbnFlg(results.system.use_portable_kbn);
          // 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
          vm.useReceiptMWMFlg(results.system.use_receipt_mwm);
          // 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応

          // 2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
          //検査進捗ボタンフラグ
          vm.statusColorFlg(results.system.statuscolor_flg);
          // 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

          vm.uketukeMaster(results.master.uketuke_array);
          vm.byoutouMaster(results.master.byoutou_array);
          vm.kensaTypeMaster(results.master.kensatype_array);
          vm.kensasituMaster(results.master.kensasitu_array);
          vm.statusMaster(results.master.status_array);
          vm.templateMaster(results.master.template_array);
          vm.nyugaiMaster(results.master.nyugai_array);
          vm.portableMaster(results.master.portable_array);
          // 2020.09.14 Add Nishihara@COSMO start 実施技師登録機能追加対応
          vm.zisshishaMaster(results.master.zisshisha_array);
          // 2020.09.14 Add Nishihara@COSMO end 実施技師登録機能追加対応

          vm.loginForm(null);
          vm.orderDetailForm(null);
          vm.orderListObj = new orderListVM();
          vm.orderListForm(vm.orderListObj);
          vm.orderListForm().initialize(true);
          // 2018.12.12 Add H.Watanabe@COSMO Start 呼出参照機能対応
          if (vm.isReadMode()) {
        	// 2020.09.09 Mod Nishihara@COSMO start 進捗ボタンの表示制御 呼出削除対応
        	  //参照モードの初回検索時、全ステータスで検索(全表示ボタンと同じ検索条件)
//            vm.orderListForm().search(2);
        	  vm.orderListForm().search(null);
        	// 2020.08.26 Add Nishihara@COSMO end 進捗ボタンの表示制御 呼出削除対応
          } else {
            vm.orderListForm().search(10);

            // 2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
            //検査進捗ボタンフラグがtrueだったら、受済ボタンを設定ファイルで設定したONの時の色で表示する。(ログイン時)
            if(vm.statusColorFlg()){
            	vm.getStatusColor_conf(10);
            }
            // 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応
          }
          // 2018.12.12 Add H.Watanabe@COSMO End   呼出参照機能対応
        }
      }finally{
        vm.hideProgressDialog();
      }
    });
  };

  // 再ログイン通信
  // 2018.12.20 Mod H.Watanabe@COSMO Start 呼出参照機能対応
  // vm.doRelogin = function() {
  vm.doRelogin = function(mode) {
  // 2018.12.20 Mod H.Watanabe@COSMO End   呼出参照機能対応
    vm.showProgressDialog();
    async.series({
      login: function(callback){
        var params = {
          userid: vm.reloginModalDlg().userId(),
          password: vm.reloginModalDlg().password(),
          // 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
          mode: mode
          // 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
        };
        comm.post("login", params, callback);
      },

      system: function(callback){
        comm.get("system", null, callback);
      },

    }, function(err, results){
      try{
        // 再ログインダイアログを閉じる
        vm.hideReloginDialog();

        if(err){
          if(err.errlevel && (err.errlevel == "WARN")){
            // メッセージダイアログを表示
            vm.showMessageDialog(err.message, null, function(){
              // メッセージダイアログを閉じたら再ログインを継続
              vm.showReloginDialog();
            });
          }else{
            throw new Error(err);
          }
        }else{
          vm.systemDate(results.system.sysdate);
          vm.kanjaIdLength(results.system.kanjaidlen);
          vm.renrakuMemoLength(results.system.renrakumemolen);
        }
      }finally{
        vm.hideProgressDialog();
      }
    });
  };

  // 一覧画面→ログイン画面
  vm.doLogout = function() {
    vm.showMessageDialog("ログアウトします。よろしいですか？", ["はい", "いいえ"], function(result){
      if(result == 0){
        vm.loginForm(new loginVM());
        vm.orderListForm(null);
        vm.orderDetailForm(null);
      }
    });
  };
  
  // 2023.05.23 Mod M.Miyazaki@Cosmo ログイン時の状態に戻すボタンの追加 Start
  // 全クリアボタン(ログイン時の状態に戻す)
  vm.allClear = function() {
	//2023.11.14 Mod Kasama@COSMO start 一覧の「クリア」ボタンの機能を「IDクリア」に統合
	  /*vm.showMessageDialog("全クリアします。よろしいですか？", ["はい", "いいえ"], function(result){
      if(result == 0){
        vm.orderListObj = new orderListVM();
        vm.orderListForm(vm.orderListObj);
        vm.orderListForm().initialize(true);
      }
    });*/
	  //患者IDのみクリア、初期化処理で検索条件の初期化は行わない(false)
	  vm.orderListForm().clearKanjaId();
      vm.orderListForm().initialize(false);
      //2023.11.14 Mod Kasama@COSMO end 一覧の「クリア」ボタンの機能を「IDクリア」に統合
  };
  // 2023.05.23 Mod M.Miyazaki@Cosmo ログイン時の状態に戻すボタンの追加 End

  // 一覧画面→詳細画面
  //2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
  vm.openDetail = function(order, isMWM) {
  //vm.openDetail = function(order) {
  // 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
    vm.showProgressDialog();
    async.series({
      orderdetail: function(callback){
        var params = {
          ris_id: order.ris_id
        };
        comm.get("orderdetail", params, callback);
      },
    }, function(err, results){
      try{
        if(err){
          if(err.errlevel && (err.errlevel == "WARN")){
            vm.showMessageDialog(err.message, null, function(){
              vm.orderListForm().refresh();
            });
          }else if(err.errlevel && (err.errlevel == "NO_SESSION")){
            // セッションタイムアウト時は再ログインダイアログを表示
            vm.showReloginDialog();
          }else{
            throw new Error(err);
          }
        }else{
          // 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
          if(isMWM){
            vm.orderDetailForm(new orderDetailVM(results.orderdetail.order_detail, isMWM));
          }
          // 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
          else{
            vm.orderDetailForm(new orderDetailVM(results.orderdetail.order_detail));
          }
          vm.orderListForm(null);
        }
      }finally{
        vm.hideProgressDialog();
      }
    });
  };

  // 詳細画面→一覧画面
  vm.closeDetail = function(doRefresh) {
    vm.orderListForm(vm.orderListObj);
    vm.orderListForm().initialize(false);
    vm.orderListForm().clearKanjaId();
    if(doRefresh){
      vm.orderListForm().refresh();
    }

    vm.orderDetailForm(null);

    // 2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
    //検査進捗ボタンフラグがtrueだったら、詳細画面に遷移する前の検査進捗ボタンの色で表示する(詳細画面で戻るボタン押下時)
    if(vm.statusColorFlg()){
  	  vm.getStatusColor_conf(vm.orderListObj.lastSearchParams.status);
    }
    // 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

  };

  //DBから取得した検査浸食ボタンの色を使用する時に通る処理(設定ファイルの検査進捗ボタンフラグがfalseの時)
  vm.getStatusColor = function(code){
    return util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color", code));
  };

  vm.getStatusColorBk = function(code){
    return util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk", code));
  };

  //2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
  //各検査進捗ボタンのON/OFF時の背景色と文字色(設定ファイルから取得した色)をそれぞれ設定
  vm.getStatusColor_conf = function(status){
		document.getElementById('miuke').style.backgroundColor  = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_OFF", 0));
		document.getElementById('yobidashi').style.backgroundColor  = util.numToColor(util.getMasterValue(vm.statusMaster(),"statuscode", "colorbk_OFF", 2));
		document.getElementById('ukezumi').style.backgroundColor  = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_OFF", 10));
		//document.getElementById('kenchu').style.backgroundColor  = util.numToColor(util.getMasterValue(vm.statusMaster(),"statuscode", "colorbk_OFF", 20));
		document.getElementById('horyu').style.backgroundColor  = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_OFF", 21));
		document.getElementById('kenzumi').style.backgroundColor  = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_OFF", 90));
		document.getElementById('miuke').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_OFF", 0));
		document.getElementById('yobidashi').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_OFF", 2));
		document.getElementById('ukezumi').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_OFF", 10));
		//document.getElementById('kenchu').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_OFF", 20));
		document.getElementById('horyu').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_OFF", 21));
		document.getElementById('kenzumi').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_OFF", 90));
	  if(status == '0'){
		document.getElementById('miuke').style.backgroundColor = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_ON", status));
		document.getElementById('miuke').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_ON", status));
	  }
	  else if(status == '2'){
		document.getElementById('yobidashi').style.backgroundColor = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_ON", status));
		document.getElementById('yobidashi').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_ON", status));
	  }
	  else if(status == '10'){
		document.getElementById('ukezumi').style.backgroundColor = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_ON", status));
		document.getElementById('ukezumi').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_ON", status));
	  }
	  //else if(status == '20'){
		//	document.getElementById('kenchu').style.backgroundColor = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_ON", status));
		//	document.getElementById('kenchu').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_ON", status));
	  //}
	  else if(status == '21'){
		document.getElementById('horyu').style.backgroundColor = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_ON", status));
		document.getElementById('horyu').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_ON", status));
	  }
	  else if(status == '90'){
		document.getElementById('kenzumi').style.backgroundColor = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "colorbk_ON", status));
		document.getElementById('kenzumi').style.color = util.numToColor(util.getMasterValue(vm.statusMaster(), "statuscode", "color_ON", status));
	  }
  };
  //2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応


  vm.getStatusLabel = function(code){
    return util.getMasterValue(vm.statusMaster(), "statuscode", "shortlabel", code);
  };

  //プルダウンを手動で変更した時に発火
  vm.changeKensasitu = function(){
    vm.selectedKensaKiki(undefined);
    if(vm.selectedKensasitu()){
      var ks = ko.utils.arrayFirst(
        vm.kensasituMaster(),
        function(item){
          return item.id == vm.selectedKensasitu();
        });
      vm.kensaKikiMaster(ks.kensakiki_array);
    }else{
      vm.kensaKikiMaster([]);
    }
  };


  //2020.08.28 Add Nishihara@COSMO start MWM対象装置の制御追加(検査室・検査機器)
  //画面の初期値でプルダウンに入れる値の設定でログイン後発火
  //※上記vm.changeKensasituとは発火点が違うため、同じ処理があっても消さないこと
  vm.initialKensasitu = function(){
	vm.selectedKensaKiki(undefined);
	if(vm.beforselectedKensasitu()){
	  var ks = ko.utils.arrayFirst(
	    vm.kensasituMaster(),
	    function(kensasitu){
	      return kensasitu.id == vm.beforselectedKensasitu();
	    });

	  vm.kensaKikiMaster(ks.kensakiki_array);

	  var ks = ko.utils.arrayFirst(
		 ks.kensakiki_array,
		 function(kensakiki){
		   return kensakiki.id == vm.beforselectedKensakiki();
		 });
	}else{
	  vm.kensaKikiMaster([]);
	}
  };
  //2020.08.28 Add Nishihara@COSMO end MWM対象装置の制御追加(検査室・検査機器)

  //// 再ログインダイアログ
  // 再ログインダイアログViewModel
  var reloginModalDlgVM = function(){
    var self = this;
    self.userId = ko.observable("");
    self.password = ko.observable("");
  };

  // 再ログインダイアログ表示メソッド
  vm.showReloginDialog = function(){
    vm.reloginModalDlg(new reloginModalDlgVM());
    vm.reloginModalDlg().userId(vm.loginUserId());
    $("#reloginModal").modal();
    $("#inputPassword2").focus();
  };

  //再ログインダイアログ非表示メソッド
  vm.hideReloginDialog = function(){
    $("#reloginModal").modal("hide");
    vm.reloginModalDlg(null);
  };

  //// メッセージダイアログ
  // メッセージダイアログViewModel
  var messageModalDlgVM = function(msg, buttons){
    var self = this;
    self.buttons = ko.observableArray(buttons || ["閉じる"]);
    self.message = ko.observable(msg);
    self.result = null;
    self.clickButton = function(index){
      self.result = index;
    };
  };

  // メッセージダイアログ表示メソッド
  vm.showMessageDialog = function(msg, buttons, callback){
    vm.messageModalDlg(new messageModalDlgVM(msg, buttons));
    $('#messageModal').on('hidden.bs.modal', function () {
      var result = vm.messageModalDlg().result;
      vm.messageModalDlg(null);
      if(callback){
        callback(result);
      }
    });
    $("#messageModal").modal();
  };

  //// エラーダイアログ
  var errorModalDlgVM = function(error){
    var self = this;
    self.error = ko.observable(error);
  };

  // エラーダイアログ表示メソッド
  vm.showErrorDialog = function(error, callback){
    vm.errorModalDlg(new errorModalDlgVM(error));
    $("#errorModal").on("hidden.bs.modal", function(){
      if(callback){
        callback();
      }
    });
    $("#errorModal").modal();
  };

  //// プログレスダイアログ
  var progressCall = 0;
  // プログレスダイアログViewModel
  var progressModalDlgVM = function(){
    var self = this;
  };

  // プログレスダイアログ表示メソッド
  vm.showProgressDialog = function(){
    if(progressCall == 0){
      vm.progressModalDlg(new progressModalDlgVM());
      $("#progressModal").modal();
    }
    progressCall++;
  };

  vm.hideProgressDialog = function(){
    progressCall--;
    if(progressCall == 0){
      $("#progressModal").modal("hide");
      vm.progressModalDlg(null);
    }
  };

  ko.applyBindings(vm);

  window.onerror = function (msg, file, line, column, err) {
    var s =
      "msg: " + msg + "\n" +
      "file: " + file + "\n" +
      "line: " + line + "\n";
    if(column){
      s += "column: " + column + "\n";
    }
    if(err){
      s += "stack: " + err.stack+ "\n";
    }
    vm.showErrorDialog(
      s,
      function(){
        vm.loginForm(new loginVM());
        vm.orderListForm(null);
        vm.orderDetailForm(null);
      }
    );
  };
});
