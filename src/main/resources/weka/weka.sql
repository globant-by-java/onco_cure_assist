select p.gender,
       p.age_class,

       t.surgery_applied,
       t.chemotherapy_applied,
       t.radiation_therapy_applied,

       d.tumour_size::text,
       d.bronchial_carcinoma,
       d.lung_carcinoma,
       d.peribroncial_carcinoma,
       d.interlobar,
       d.subcarinal,
       d.lower_paratracheal,
       d.upper_paratracheal,
       d.onco_anamesys,
       d.stage,
       d.complains,
       d.grade,
       d.histology_diagnosis,
       d.smoking,
       d.t,
       d.n,
       d.m,
       d.copd,
       d.tuberculomas,
       d.chd,
       d.lcd,
       d.inflammatory_digestive_processes,
       d.hepatitis,
       d.cirrhosis,
       d.pancreatitis,
       d.musculoskeletal_diseases,
       d.inflammatory_kidneys_bladder_diseases,
       d.prostate_benign_diseases,
       d.veins_diseases,
       d.blood_vessels_diseases,
       d.rhythm_disturbances,
       d.thyroid_gland_benign_diseases,
       d.nervous_diseases,
       d.strokes,
       d.rheumatic_diseases,
       d.anemia,

       gp.vegf_634,
       gp.vegf_2578,
       gp.vegf_936,
       gp.egf,
       gp.gstt,
       gp.gstm,
       gp.gstp,
       gp.natkpn,
       gp.nattag,
       gp.natbam,
       gp.acetylation_type,
       gp.cyp_1a2,
       gp.cyp_2d6,
       gp.mdr,
       gp.egfr_18_tumor,
       gp.egfr_19_tumor,
       gp.egfr_19_norm,
       gp.egfr_20_tumor,
       gp.egfr_20_norm,
       gp.egfr_21_tumor,
       gp.egfr_21_norm,
       gp.egfr_21_blood,
       gp.tgf_509,
       gp.tgf_25_codon,
       gp.tgfr_206,
       gp.kdr_1719,
       gp.kdr_906,
       gp.sult1,
       gp.mmp9_2660,
       gp.mmp9_1562,
       gp.mmp2_735,
       gp.mmp2_1575,
       gp.kras_2ex_tumor,
       gp.pik3ca_9ex,
       gp.pik3ca_20ex,
       gp.pten,
       gp.dnmt_149,
       gp.dnmt_579,
       p.class_id

from patient p
       join treatment t on p.id = t.patient_id
       join diagnostics d on p.id = d.patient_id
       join genetic_predictors gp on p.id = gp.patient_id
where p.class_id is not null