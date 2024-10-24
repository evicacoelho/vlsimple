import React, { useRef } from "react";

const MediaUploadModal = ({
    isOpen, onClose, onUpload
}: {
    isOpen: boolean; onClose: () => void; onUpload: (files:FileList) => void}) => {
        const fileInputRef = useRef<HTMLInputElement | null>(null);
        
        if (!isOpen) return null;

        const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
            if (e.target.files) {
                onUpload(e.target.files);
            }
        };

        const addWebkitDirectory = () => {
            if (fileInputRef.current) {
                fileInputRef.current.setAttribute('webkitdirectory', 'true');
            }
        };

        return (
            <div className="modal">
                <div className="modal-content">
                    <h2>Upload Media</h2>
                    <input
                        type="file"
                        multiple
                        ref={fileInputRef}
                        onClick={addWebkitDirectory}
                        onChange={handleFileChange}
                    />
                    <button onClick={onClose}>Close</button>
                </div>
            </div>
        )
    };

export default MediaUploadModal;
