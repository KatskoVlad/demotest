<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 05.02.2019
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/pvd.css" rel="stylesheet" data-skip-moving="true">
    <link href="css/min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet" >
    <link href="css/ie.css" rel="stylesheet" >

</head>
<body>
<form method="POST" autocomplete="off">
    <div class="rem-header">
        <div class="rem-title">Перевод с карты на карту</div>
    </div>
    <div class="rem-row">
        <div class="rem-col">
            <div class="card-title">Отправитель</div>
            <section class="card-shape card-shape-sender">
                <div class="card-shape-in">
                    <div class="card-shape-inputs card-sender">
                        <div class="flex-row flex-row-padding-half">
                            <div class="col-xs-12">
                                <div class="card-input">
                                    <input type="text" name="CARD" id="cardfrom" placeholder="Введите номер карты" required="">
                                </div>
                            </div>
                        </div>
                        <div class="flex-row flex-row-padding-half">
                            <div class="col-xs-6">
                                <input type="text" name="MMYY" id="mmyy" maxlength="5" placeholder="ММ/ГГ" required="">
                                <input type="HIDDEN" name="EXP" id="exp" value="" autocomplete="off" required="">
                                <input type="HIDDEN" name="EXP_YEAR" id="exp_year" value="" autocomplete="off" required="">
                            </div>
                            <div class="col-xs-6">
                                <div class="card-input">
                                    <input type="text" name="CVC2" id="cvccvv" maxlength="3" placeholder="CVC/CVV код" required="">

                                    <div class="input-graphics">
                                        <div class="ig-wrap"><img src="./Перевод «Хутка на картку»_files/cvc-code.png" alt=""></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <div class="rem-col-gap rem-col-gap-direction">
            <svg class="ico-card-direction" xmlns="http://www.w3.org/2000/svg" width="32.5px" height="58.5px"></svg>
        </div>
        <div class="rem-col">
            <div class="card-title">Получатель</div>
            <section class="card-shape">
                <div class="card-shape-in">
                    <div class="card-shape-inputs card-receiver">
                        <div class="flex-row flex-row-padding flex-row-padding-half">
                            <div class="col-xs-12">
                                <div class="card-input">
                                    <input type="text" name="PAYMENT_TO" id="cardto" maxlength="23" placeholder="Введите номер карты" required="">
                                    <div class="card-type type-visa"></div>
                                    <div class="card-type type-mastercard"></div>
                                    <div class="card-type type-maestro"></div>
                                    <div class="card-type type-belkart"></div>
                                    <div class="card-type type-unknown"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <div class="rem-row">
        <div class="rem-col">
            <div class="card-input">
                <input type="text" name="AMOUNT" id="amount" placeholder="Введите сумму перевода" maxlength="20" required="">
                <div class="input-helper">
                    <span class="info" title="Введите сумму перевода"></span>
                </div>
            </div>
        </div>
        <div class="rem-col-gap"></div>
        <div class="rem-col">
            <div class="choices" data-type="select-one" role="listbox" tabindex="0" aria-haspopup="true" aria-expanded="false" dir="ltr">
                <div class="choices__inner"><select name="CURRENCY" id="currency" data-select-currency="" class="choices__input is-hidden" tabindex="-1" style="display:none;" aria-hidden="true" data-choice="active"><option value="BYN" selected="">Белорусский рубль</option></select><div class="choices__list choices__list--single"><div class="choices__item choices__item--selectable" data-item="" data-id="1" data-value="BYN" aria-selected="true">
                    Белорусский рубль
                </div></div></div><div class="choices__list choices__list--dropdown" aria-expanded="false">
                <div class="choices__list" dir="ltr" role="listbox"><div class="choices__item choices__item--choice choices__item--selectable is-highlighted" data-select-text="" data-choice="" data-id="1" data-value="BYN" data-choice-selectable="" id="choices-currency-item-choice-1" role="option" aria-selected="true">
                    Белорусский рубль
                </div><div class="choices__item choices__item--choice choices__item--selectable" data-select-text="" data-choice="" data-id="2" data-value="EUR" data-choice-selectable="" id="choices-currency-item-choice-2" role="option">
                    Евро
                </div><div class="choices__item choices__item--choice choices__item--selectable" data-select-text="" data-choice="" data-id="3" data-value="USD" data-choice-selectable="" id="choices-currency-item-choice-3" role="option">
                    Доллар США
                </div></div></div></div>
        </div>
    </div>
    <div class="rem-row">
        <div class="rem-col">
            <div class="about-comission">
                <span>Комиссия составит</span> <span class="price"><b id="com">0,00</b> <span id="com_currency">BYN</span></span>
            </div>
        </div>
        <div class="rem-col-gap"></div>
        <div class="rem-col">
            <label>
                <input type="checkbox" name="agree" id="agree" required="">
                <span>Я согласен(-а) с условиями перевода</span>
            </label>
        </div>
    </div>
    <div class="rem-row">
        <div class="rem-col">
            <input type="HIDDEN" name="TRTYPE" value="8">
            <input type="submit" name="SEND_BUTTON" id="send_button" value="Перевести" class="button button-wide" disabled="">
        </div>
        <div class="rem-col-gap"></div>
    </div>


    <input id="order" type="HIDDEN" name="ORDER" value="" size="6" maxlength="21" placeholder="Введите номер заказа">
    <input type="HIDDEN" name="DESC" value="p2p_payment" maxlength="50">
    <input type="HIDDEN" name="TERMINAL" value="99100001" size="8" maxlength="8">
    <div style="display:none">
        <select name="CVC2_RC" size="9">
            <option selected="" value="1">CVC2 is present</option>
        </select>
    </div>

</form>
</div>
</div>
</body>
</html>
