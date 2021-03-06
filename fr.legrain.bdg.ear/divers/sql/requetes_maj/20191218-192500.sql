
--RAjout de clés Etrangères sur tables concernant les abos 
--set schema 'demo';
ALTER TABLE ta_stripe_subscription ADD CONSTRAINT fk_ta_stripe_subscription_id_abonnement FOREIGN KEY(id_abonnement) REFERENCES ta_abonnement(id_document) MATCH SIMPLE;

ALTER TABLE ta_stripe_subscription_item ADD CONSTRAINT fk_ta_stripe_subscription_item_id_stripe_plan FOREIGN KEY(id_stripe_plan) REFERENCES ta_stripe_plan(id_stripe_plan) MATCH SIMPLE;

ALTER TABLE ta_l_abonnement ADD CONSTRAINT fk_ta_l_abonnement_id_stripe_subscription_item FOREIGN KEY(id_ta_stripe_subscription_item) REFERENCES ta_stripe_subscription_item(id_stripe_subscription_item) MATCH SIMPLE;

ALTER TABLE ta_abonnement ADD CONSTRAINT fk_ta_abonnement_id_ta_stripe_subscription FOREIGN KEY(id_ta_stripe_subscription) REFERENCES ta_stripe_subscription(id_stripe_subscription) MATCH SIMPLE;

ALTER TABLE ta_stripe_paiement_prevu ADD CONSTRAINT fk_ta_stripe_paiement_prevu_id_ta_stripe_charge FOREIGN KEY(id_stripe_charge) REFERENCES ta_stripe_charge(id_stripe_charge) MATCH SIMPLE;
ALTER TABLE ta_stripe_paiement_prevu ADD CONSTRAINT fk_ta_stripe_paiement_prevu_id_ta_stripe_customer FOREIGN KEY(id_stripe_customer) REFERENCES ta_stripe_customer(id_stripe_customer) MATCH SIMPLE;
ALTER TABLE ta_stripe_paiement_prevu ADD CONSTRAINT fk_ta_stripe_paiement_prevu_id_ta_stripe_source FOREIGN KEY(id_stripe_source) REFERENCES ta_stripe_source(id_stripe_source) MATCH SIMPLE;
ALTER TABLE ta_stripe_paiement_prevu ADD CONSTRAINT fk_ta_stripe_paiement_prevu_id_stripe_subscription FOREIGN KEY(id_stripe_subscription) REFERENCES ta_stripe_subscription(id_stripe_subscription) MATCH SIMPLE;
ALTER TABLE ta_stripe_paiement_prevu ADD CONSTRAINT fk_ta_stripe_paiement_prevu_id_ta_stripe_invoice FOREIGN KEY(id_stripe_invoice) REFERENCES ta_stripe_invoice(id_stripe_invoice) MATCH SIMPLE;

ALTER TABLE ta_l_echeance ADD CONSTRAINT fk_ta_l_echeance_id_stripe_subscription FOREIGN KEY(id_stripe_subscription) REFERENCES ta_stripe_subscription(id_stripe_subscription) MATCH SIMPLE;
ALTER TABLE ta_l_echeance ADD CONSTRAINT fk_ta_l_echeance_id_stripe_subscription_item FOREIGN KEY(id_stripe_subscription_item) REFERENCES ta_stripe_subscription_item(id_stripe_subscription_item) MATCH SIMPLE;

ALTER TABLE ta_l_avis_echeance ADD CONSTRAINT fk_ta_l_avis_echeance_id_mouvement_stock_prev FOREIGN KEY(id_mouvement_stock_prev) REFERENCES ta_mouvement_stock_prev(id_mouvement_stock) MATCH SIMPLE;

ALTER TABLE ta_avis_echeance ADD CONSTRAINT fk_ta_avis_echeance_id_mise_a_disposition FOREIGN KEY(id_mise_a_disposition) REFERENCES ta_mise_a_disposition(id_mise_a_disposition) MATCH SIMPLE;
ALTER TABLE ta_avis_echeance ADD CONSTRAINT fk_ta_avis_echeance_id_gr_mouv_stock_prev FOREIGN KEY(id_gr_mouv_stock_prev) REFERENCES ta_gr_mouv_stock_prev(id_gr_mouv_stock) MATCH SIMPLE;