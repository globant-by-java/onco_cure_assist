CREATE SEQUENCE patient_id_seq;
select setval('patient_id_seq', 500, true);
CREATE SEQUENCE treatment_id_seq;
select setval('treatment_id_seq', 500, true);
CREATE SEQUENCE diagnostics_id_seq;
select setval('diagnostics_id_seq', 500, true);
CREATE SEQUENCE genetic_predictors_id_seq;
select setval('genetic_predictors_id_seq', 500, true);


CREATE TABLE patient
(
  id                  BIGINT DEFAULT nextval('patient_id_seq'::regclass) PRIMARY KEY NOT NULL,
  full_name           TEXT                                                           NOT NULL,
  gender              INTEGER                                                        NOT NULL,
  birth_date          DATE                                                           NOT NULL,
  contact_date        DATE                                                           NOT NULL,
  alive               BOOLEAN                                                        NOT NULL,
  survival_month      DOUBLE PRECISION                                               NOT NULL,
  employed            BOOLEAN,
  profession_code     INTEGER,
  address             VARCHAR(512),
  phone               VARCHAR(128),
  patient_card_number VARCHAR(64)                                                    NOT NULL,
  class_id            INTEGER,
  age                 INTEGER,
  age_class           INTEGER
);

CREATE TABLE treatment
(
  id                        BIGINT DEFAULT nextval('treatment_id_seq'::regclass) PRIMARY KEY NOT NULL,
  surgery_applied           BOOLEAN                                                          NOT NULL,
  chemotherapy_applied      BOOLEAN                                                          NOT NULL,
  radiation_therapy_applied BOOLEAN,
  surgery_code              INTEGER,
  surgeon_name              VARCHAR,
  patient_id                BIGINT                                                           NOT NULL,
  surgery_date              DATE,
  first_line_course         INTEGER,
  second_line_course        INTEGER,
  third_line_course         INTEGER,
  CONSTRAINT patient_fk FOREIGN KEY (patient_id) REFERENCES patient (id)
);

CREATE TABLE diagnostics
(
  id                                    BIGINT  DEFAULT nextval('diagnostics_id_seq'::regclass) PRIMARY KEY NOT NULL,
  patient_id                            BIGINT                                                              NOT NULL,
  tnm                                   VARCHAR(16)                                                         NOT NULL,
  primary_tumour                        INTEGER,
  regional_lymph_nodes                  INTEGER,
  distant_metastasis                    INTEGER,
  tumour_size                           BIGINT,
  side                                  INTEGER,
  bronchial_carcinoma                   BOOLEAN,
  lung_carcinoma                        BOOLEAN,
  peribroncial_carcinoma                BOOLEAN,
  interlobar                            BOOLEAN,
  subcarinal                            BOOLEAN,
  lower_paratracheal                    BOOLEAN,
  upper_paratracheal                    BOOLEAN,
  same_lung_metastasis                  BOOLEAN,
  symptoms                              BOOLEAN,
  vlc                                   DOUBLE PRECISION,
  tvc                                   DOUBLE PRECISION,
  tiff_number                           DOUBLE PRECISION,
  volume_force_exp                      DOUBLE PRECISION,
  heart_rate                            INTEGER,
  heart_block                           BOOLEAN,
  extrasistols                          BOOLEAN,
  somatic                               BOOLEAN,
  bac                                   BOOLEAN,
  onco_anamesys                         BOOLEAN,
  stage                                 VARCHAR(16),
  complains                             BOOLEAN DEFAULT false,
  grade                                 INTEGER,
  histology_diagnosis                   INTEGER,
  smoking                               BOOLEAN,
  histology_code                        VARCHAR(64),
  t                                     VARCHAR(16),
  n                                     VARCHAR(16),
  m                                     VARCHAR(16),
  copd                                  BOOLEAN,
  tuberculomas                          BOOLEAN,
  chd                                   BOOLEAN,
  lcd                                   BOOLEAN,
  inflammatory_digestive_processes      BOOLEAN,
  hepatitis                             BOOLEAN,
  cirrhosis                             BOOLEAN,
  pancreatitis                          BOOLEAN,
  musculoskeletal_diseases              BOOLEAN,
  inflammatory_kidneys_bladder_diseases BOOLEAN,
  prostate_benign_diseases              BOOLEAN,
  veins_diseases                        BOOLEAN,
  blood_vessels_diseases                BOOLEAN,
  rhythm_disturbances                   BOOLEAN,
  thyroid_gland_benign_diseases         BOOLEAN,
  nervous_diseases                      BOOLEAN,
  strokes                               BOOLEAN,
  rheumatic_diseases                    BOOLEAN,
  anemia                                BOOLEAN,
  CONSTRAINT patient_fk FOREIGN KEY (patient_id) REFERENCES patient (id)
);

CREATE TABLE genetic_predictors
(
  id               BIGINT DEFAULT nextval('genetic_predictors_id_seq'::regclass) PRIMARY KEY NOT NULL,
  patient_id       BIGINT                                                                    NOT NULL,
  vegf_634         VARCHAR(64),
  vegf_2578        VARCHAR(64),
  vegf_936         VARCHAR(64),
  egf              VARCHAR(64),
  gstt             VARCHAR(64),
  gstm             VARCHAR(64),
  gstp             VARCHAR(64),
  natkpn           VARCHAR(64),
  nattag           VARCHAR(64),
  natbam           VARCHAR(64),
  acetylation_type VARCHAR(64),
  cyp_1a2          VARCHAR(64),
  cyp_2d6          VARCHAR(64),
  mdr              VARCHAR(64),
  egfr_18_tumor    VARCHAR(64),
  egfr_18_norm     VARCHAR(64),
  egfr_19_tumor    VARCHAR(64),
  egfr_19_norm     VARCHAR(64),
  egfr_20_tumor    VARCHAR(64),
  egfr_20_norm     VARCHAR(64),
  egfr_21_tumor    VARCHAR(64),
  egfr_21_norm     VARCHAR(64),
  egfr_21_blood    VARCHAR(64),
  tgf_509          VARCHAR(64),
  tgf_25_codon     VARCHAR(64),
  tgfr_206         VARCHAR(64),
  kdr_1719         VARCHAR(64),
  kdr_906          VARCHAR(64),
  sult1            VARCHAR(64),
  mmp9_2660        VARCHAR(64),
  mmp9_1562        VARCHAR(64),
  mmp2_735         VARCHAR(64),
  mmp2_1575        VARCHAR(64),
  kras_2ex_tumor   VARCHAR(64),
  kras_2ex_norm    VARCHAR(64),
  pik3ca_9ex       VARCHAR(64),
  pik3ca_20ex      VARCHAR(64),
  pten             VARCHAR(64),
  dnmt_149         VARCHAR(64),
  dnmt_579         VARCHAR(64),
  CONSTRAINT patient_fkey FOREIGN KEY (patient_id) REFERENCES patient (id)
);

CREATE UNIQUE INDEX patient_card_pk ON patient (patient_card_number);

